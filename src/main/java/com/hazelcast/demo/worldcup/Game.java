package com.hazelcast.demo.worldcup;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * Data about a game.
 * </p>
 */
@SuppressWarnings("serial")
@Data
public class Game implements Comparable<Game>, Serializable {

	private String description;
	private String hashtag;
	
	/* Sort on hashtag only
	 */
	@Override
	public int compareTo(Game that) {
		return this.hashtag.compareTo(that.getHashtag());
	}
}
