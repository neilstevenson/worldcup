package com.hazelcast.demo.worldcup.jet;

/**
 * <p>
 * Is the identified country one of the ones we are searching
 * for.
 * </p>
 * <p>Hashtag here expected to be '#' + three chars for home
 * team + three chars for away team.
 * </p>
 */
public class MyFilterOnTeam {
	
    public static boolean keyIsInHashtag(String key, String hashtag) {
    	if (hashtag.length()!=7) {
    		return false;
    	}
    	if (key.equalsIgnoreCase(hashtag.substring(1, 4))
    		|| key.equalsIgnoreCase(hashtag.substring(4, 7))) {
    		return true;
    	}
    	return false;
    }

}
