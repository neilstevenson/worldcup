package com.hazelcast.demo.worldcup.jet;

import com.hazelcast.demo.worldcup.Sentiment;
import com.hazelcast.jet.datamodel.Tuple2;

/**
 * <p>
 * Simplistic assessment of the sentiment of a tweet, by looking for keywords.
 * </p>
 * <p>
 * <u>Very Simplistic!</u>. So the word "<i>good</i>" will result in an upvote.
 * This version isn't bright enough to spot that "<i>good</i>" and "<i>not good</i>"
 * convey different sentiments. It only sees the word "<i>good</i>" and thinks
 * goodt things.
 * </p> 
 */
public class MyDetermineSentiment {
	
	private static final String[] NEGATIVE_WORDS_LOWER_CASE = {
			"bad", "hopeless", "pathetic", "rubbish",
			"mal", "peor" /* Spanish */
	};
	private static final String[] NEUTRAL_WORDS_LOWER_CASE= {
			"draw", "ok"
	};
	private static final String[] POSITIVE_WORDS_LOWER_CASE = {
			"amazing", "good", "great",
			"ganar" /* Spanish */
	};

	public static Tuple2<String, Sentiment> determineSentiment(Tuple2<String, String> entry, String hashtag) {
		// Is this tweet about the home or away team
		boolean home = hashtag.substring(1, 4).endsWith(entry.getKey());
		
		Sentiment sentiment = new Sentiment();
		Tuple2<String, Sentiment> result = Tuple2.tuple2(entry.getKey(), sentiment);
		
		if (home) {
			sentiment.setHomeNegative(assess(entry.getValue(), NEGATIVE_WORDS_LOWER_CASE));
			sentiment.setHomeNeutral(assess(entry.getValue(), NEUTRAL_WORDS_LOWER_CASE));
			sentiment.setHomePositive(assess(entry.getValue(), POSITIVE_WORDS_LOWER_CASE));
		} else {
			sentiment.setAwayNegative(assess(entry.getValue(), NEGATIVE_WORDS_LOWER_CASE));
			sentiment.setAwayNeutral(assess(entry.getValue(), NEUTRAL_WORDS_LOWER_CASE));
			sentiment.setAwayPositive(assess(entry.getValue(), POSITIVE_WORDS_LOWER_CASE));
		}
		
		return result;
	}
	
	/* A simpler form of Word Count :-)
	 */
	private static long assess(String text, String[] words) {
		int count = 0;
		
		for (int i = 0 ; i < words.length; i++) {
			if (text.contains(words[i])) {
				count++;
			}
		}
		
		return count;
	}
	
}
