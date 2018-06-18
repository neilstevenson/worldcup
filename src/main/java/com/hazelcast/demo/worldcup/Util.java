package com.hazelcast.demo.worldcup;

import java.util.Collection;

import com.hazelcast.core.MultiMap;

/**
 * <p>
 * Helpful functions needed in more than one place.
 * </p>
 */
public class Util {
	private static final String ALPHABET_UPPERCASE = "abcdefghijklmnopqrstuvwxyz".toUpperCase();

	/**
	 * <p>
	 * Ensure String is a hashtag, begins with exactly one hash.
	 * </p>
	 * 
	 * @param A String, could be empty
	 * @return Input possibly prepended with one char
	 * @throws Exception if String empty
	 */
	public static String makeHashtag(String arg0) throws Exception {
		String s = arg0;
		while (s.charAt(0) == '#') {
			s = s.substring(1);
		}
		for (int i=0 ; i < s.length() ; i++) {
			if (ALPHABET_UPPERCASE.indexOf(s.toUpperCase().charAt(i)) == -1) {
				throw new Exception("Only letters allowed, not '" + s.charAt(i) + "' in '" + arg0 + "'");
			}
		}
		return '#' + s;
	}

	/**
	 * <p>
	 * Confirm or deny if a supplied hashtag is in the multimap of games.
	 * </p>
	 * 
	 * @param gamesMap The MultiMap holding game information 
	 * @param hashtag What to look for
	 * @return True if found
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean validateHashtag(MultiMap gamesMap, String hashtag) {
		
		Collection<Game> allGames = gamesMap.values();
		
		for (Game game : allGames) {
			if (game.getHashtag().equals(hashtag)) {
				return true;
			}
		}
		
		return false;
	}
}
