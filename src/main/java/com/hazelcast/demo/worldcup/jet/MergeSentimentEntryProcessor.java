package com.hazelcast.demo.worldcup.jet;

import java.util.Map.Entry;

import com.hazelcast.demo.worldcup.Sentiment;
import com.hazelcast.map.AbstractEntryProcessor;

/**
 * <p>
 * Combine the sentiment provided as a constructor argument with the value
 * already stored in Hazelcast's {@link com.hazelcast.core.IMap IMap}.
 * </p>
 * <p>
 * This meanms the Jet job can be running continously but we see results as they
 * are derived rather than have to end the job.
 * </p>
 * <p>
 * Hazelcast IMDG (<a href="https://hazelcast.org/>IMDG</a>) has more work to
 * do on a save than Hazelcast Jet (<a href="https://jet.hazelcast.org/>Jet</a>),
 * such as checking whether event listeners need to fire. Consequently this
 * mechanism needs Jet to have reduced the incoming data volume to a manageable
 * level before IMDG sees it.
 * </p>
 */
@SuppressWarnings("serial")
public class MergeSentimentEntryProcessor extends AbstractEntryProcessor<String, Sentiment> {
	
	private Sentiment sentiment;
	
	public MergeSentimentEntryProcessor(Sentiment arg0) {
		this.sentiment = arg0;
	}

	/**
	 * <p>
	 * Business logic : if we have sentiment figures, audment them. If
	 * we don't, save as new.
	 * </p>
	 */
	@Override
	public Object process(Entry<String, Sentiment> entry) {
		// Get old
		Sentiment oldValue = entry.getValue();

		// Merge if existing
		if (oldValue != null) {
			sentiment.setHomeNegative(sentiment.getHomeNegative() + oldValue.getHomeNegative());
			sentiment.setHomeNeutral(sentiment.getHomeNeutral() + oldValue.getHomeNeutral());
			sentiment.setHomePositive(sentiment.getHomePositive() + oldValue.getHomePositive());
			
			sentiment.setAwayNegative(sentiment.getAwayNegative() + oldValue.getAwayNegative());
			sentiment.setAwayNeutral(sentiment.getAwayNeutral() + oldValue.getAwayNeutral());
			sentiment.setAwayPositive(sentiment.getAwayPositive() + oldValue.getAwayPositive());
		}
		
		// Save
		entry.setValue(sentiment);
		
		// Caller doesn't care about return value
		return null;
	}

}
