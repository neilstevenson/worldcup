package com.hazelcast.demo.worldcup.jet;

import java.util.Arrays;
import java.util.HashMap;

import com.hazelcast.demo.worldcup.ApplicationConstants;
import com.hazelcast.jet.datamodel.Tuple2;

/**
 * <p>
 * Determine if the tweet contains exactly on team name.
 * </p>
 * <p>
 * Teams are matched exactly, so look for "<i>Sweden</i>"
 * but not "<i>SWEDEN</i>" or "<i>sweden</i>", chosing
 * simplicity over full functionality for a demo.
 * </p>
 */
public class MyDetermineTeam {
	
	private static final HashMap<String, String> TEAMS;
	private static final String[] TEAMS_KEYS;
	
	static {
		TEAMS = new HashMap<>();
		Arrays.stream(ApplicationConstants.TEAMS).forEach(line -> {
			TEAMS.put(line[0], line[1]);
		});
		
		TEAMS_KEYS = TEAMS.keySet().toArray(new String[TEAMS.size()]);
	}

	public static Tuple2<String, String> determineTeam(String text) {
		int count = 0;
		String team = "";
		
		for (int i = 0; i < TEAMS_KEYS.length; i++) {
			if (text.contains(TEAMS_KEYS[i])) {
				count++;
				if (count == 1) {
					team = TEAMS.get(TEAMS_KEYS[i]);
				} else {
					team = "";
				}
			}
		}
		
		return Tuple2.tuple2(team, text);
	}
}
