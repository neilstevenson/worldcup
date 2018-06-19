package com.hazelcast.demo.worldcup;

/**
 * <p>
 * Useful constants.
 * </p>
 */
public class ApplicationConstants {

	// Hazelcast
	public static final String IMAP_NAME_SENTINMENT = "sentiment";
	public static final String[] IMAP_NAMES = { IMAP_NAME_SENTINMENT };
	public static final String MULTIMAP_NAME_GAMES = "games";
	public static final String[] MULTIIMAP_NAMES = { MULTIMAP_NAME_GAMES };

	// Some of the games
	public static final String[][] GAMES = new String[][] {
		{ "14th June", "The World Cup", "#WORLDCUP" },
		{ "14th June", "Russia v Saudi Arabia", "#RUSKSA" },
		{ "15th June", "Portugal v Spain", "#PORSPA" },
		{ "16th June", "Argentina v Iceland", "#ARGISL"},
		{ "17th June", "Costa Rica v Serbia", "#CRCSRB" },
		{ "17th June", "Germany v Mexico", "#GERMEX" },
		{ "17th June", "Brazil v Switzerland", "#BRASWI" },
		{ "18th June", "Sweden v South Korea", "#SWEKOR" },
		{ "18th June", "Belgium v Panama", "#BELPAN" },
		{ "18th June", "Tunisia v England", "#TUNENG" },
		{ "19th June", "Russia v Egypt", "#RUSEGY" },
		{ "20th June", "Uruguay v Saudi Arabia", "#URUKSA" },
		{ "20th June", "Iran v Spain", "#IRNSPA" },
		{ "21st June", "Denmark v Australia", "#DENAUS" },
		{ "22nd June", "Brasil v Costa Rica", "#BRACRC" },
		{ "25th June", "Uruguay v Russia", "#URURUS" },
		{ "26th June", "Denmark v France", "#DENFRA" },
		{ "27th June", "Switzerland v Costa Rica", "#SWICRC" },
		{ "28th June", "England v Belgium", "#ENGBEL" },
	};
	
	// "Recommended" country trios hashtags
	public static final String[][] TEAMS = new String[][] {
		{ "Argentina", "ARG" },
		{ "Australia", "AUS" },
		{ "Belgium", "BEL" },
		{ "Brazil", "BRA" },
		{ "Colombia", "COL" },
		{ "Costa Rica", "CRC" },
		{ "Croatia", "CRO" },
		{ "Denmark", "DEN" },
		{ "Egypt", "EGY" },
		{ "England", "ENG" },
		{ "Spain", "SPA" },
		{ "France", "FRA" },
		{ "Germany", "GER" },
		{ "Iran", "IRN" },
		{ "Iceland", "ISL" },
		{ "Japan", "JAP" },
		{ "South Korea", "KOR" },
		{ "Saudi Arabia", "KSA" },
		{ "Mexico", "MEX" },
		{ "Morocco", "MAR" },
		{ "Nigeria", "NGA" },
		{ "Panama", "PAN" },
		{ "Peru", "PER" },
		{ "Poland", "POL" },
		{ "Portugal", "POR" },
		{ "Russia", "RUS" },
		{ "Senegal", "SEN" },
		{ "Serbia", "SRB" },
		{ "Switzerland", "SUI" },
		{ "Sweden", "SWD" },
		{ "Tunisia", "TUN" },
		{ "Uruguay", "URU" },
	};

}
