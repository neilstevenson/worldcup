package com.hazelcast.demo.worldcup.jet;

import java.util.Properties;

import org.springframework.stereotype.Component;

import com.hazelcast.demo.worldcup.ApplicationConstants;
import com.hazelcast.demo.worldcup.MyConfigurationProperties;
import com.hazelcast.demo.worldcup.Sentiment;
import com.hazelcast.jet.function.DistributedFunctions;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.StreamStage;
import com.hazelcast.map.EntryProcessor;

/**
 * <p>
 * A 7-stage processing pipeline to analyse Twitter data, of which there could be a large
 * amount, and store a small amount -- then sentiment expressed in those tweets.
 * </p>
 * <p>
 * TODO
 * </p>
 * <p>
 * This pipeline is essentially sequential, as the name "<i>pipeline</i>" suggests
 * with two minor observations around this.
 * </p>
 * <ol>
 * <li><p><b>Step 2B</b></p>
 * <p>
 * Step 1 creates a {@link com.hazelcast.jet.pipeline.StreamStage StreamStage}, as a
 * Java object named "{@code start}", a step in the pipeline that creates a continuous (not batch)
 * stream of data.
 * </p>
 * <p>Step 2 takes the input from step 1 and passes it's output to step 3. Step 3 to step 4. Step 4
 * to step 5. Etc
 * </p>
 * <p>However, note "{@code Step 2b}". This *alos* takes the output from step 1. So the output from
 * Step 1 passes both into Step 2 and Step 2B. There is a branch in the chain, the same output from
 * step 1 goes to both, it's not 50/50, round-robin or whatever. Both subsequent chains get all
 * input from Step 1.
 * </p>
 * <p>The reason this is done is so Step 2B can dump it's input to the console, to demonstrate
 * more easily that Jet is actually doing something. After all, there may be nothing found in
 * Twitter for the given hashtag.
 * </p>
 * <p>This Step 2B is optional, remove it if you prefer.
 * </p>
 * </li>
 * <li><p><b>Step 7</b></p>
 * <p>Step 7 uses the "{@code DistributedFunctions.entryKey()}" method.
 * </p>
 * <p>This is for group, the output from Step 6 are grouped by the key of
 * Step 6's output. In other words, Step 6 is emitting {@code Map.Entry}
 * values. These are distributed based on the key -- and the key here is
 * the name of a team "{@code DEN}" for Denmark for example.
 * </p>
 * <p>
 * This means one instance of a Jet job running in one JVM may send its
 * output to another instance of this job, potentially running in a
 * different JVM. All entries for key "{@code DEN}" for Denmark are
 * routed to a single instance of this job. 
 * </p>
 * <p>
 * A Jet job will typically run as many instances as CPUs can support
 * per JVM, and there are typically multiple JVMs in the cluster.
 * </p>
 * <p>So what this means in effect is a single instance of the
 * entry processor class looks after the collation of sentiment
 * for a team.
 * </p>
 * <p>As this routing means moving data from one JVM to another,
 * across a network, it is best to do this as far down the
 * pipeline as possible so that data volumes are reduced. Here
 * it is the last stage, can't do better than that.
 * </p>
 * </li>
 * </ol>
 * </br>
 * <p><b>IMprovements possible</b>
 * </p>
 * <p>There are many, here are some ideas
 * </p>
 * <ol>
 * <li>
 * <p><b>Geographic Exclusion</b></p>
 * <p>
 * If enabled by the user, some tweets can have their geographic position.
 * A tweet originitating in Brasil about "@{code BRA}" (Brasil) is probably
 * a home fan and they are inclined to be biassed about their team.
 * </p>
 * <p>
 * We could simply exclude these. Or we could dilute the rating for positive
 * comments since we expect them to support their side, yet note negative
 * comments since they are less likely to be lying if they are saying their
 * side is poor.
 * </p>
 * </li>
 * 
 * </ol>
 */
@Component
public class TwitterPipeline {
	
	private Properties properties;

	TwitterPipeline(MyConfigurationProperties myConfigurationProperties) throws Exception {
		// For Twitter logon
		this.properties = new Properties();
		this.properties.put(MyConfigurationProperties.CONSUMER_KEY, myConfigurationProperties.getConsumerKey());
		this.properties.put(MyConfigurationProperties.CONSUMER_SECRET, myConfigurationProperties.getConsumerSecret());
		this.properties.put(MyConfigurationProperties.TOKEN, myConfigurationProperties.getToken());
		this.properties.put(MyConfigurationProperties.TOKEN_SECRET, myConfigurationProperties.getTokenSecret());
	}

	public Pipeline build(String hashtag) throws Exception {
		Pipeline pipeline = Pipeline.create();

		// (1) Pull from Twitter by hashtag
		StreamStage<String> start =
				pipeline
				.drawFrom(StreamTwitterP.streamTwitter(this.properties, hashtag));
		
		// (2) Remove Tweets if the charset doesn't allow for easy interpretation
		start
        .filter(MyFilterOnCharset::westernChars)

        // (3) Try to determine the tweeter's team
        .map(MyDetermineTeam::determineTeam)
        
        // (4) Drop if can't determine tweeter's team abbreviation
        .filter(entry -> entry.getKey().length() == 3)

        // (5) Drop if not a team in the hashtag
        .filter(entry -> MyFilterOnTeam.keyIsInHashtag(entry.getKey(), hashtag))
        
        // (6) Determine sentiment, positive, negative or neutral
        .map(entry -> MyDetermineSentiment.determineSentiment(entry, hashtag))

        // (7) *MERGE* sentiment into map, this may cross JVM to find where the entry is stored
        .drainTo(
               Sinks.mapWithEntryProcessor(ApplicationConstants.IMAP_NAME_SENTINMENT,
                   DistributedFunctions.entryKey(),
                    entry -> ((EntryProcessor<String, Sentiment>) new MergeSentimentEntryProcessor(entry.getValue()))
             )
            )
        ;

		// (2b) [Optional] debug logging to screen of unfiltered tweets
		start
		.drainTo(Sinks.logger());
		
		return pipeline;
	}
}
