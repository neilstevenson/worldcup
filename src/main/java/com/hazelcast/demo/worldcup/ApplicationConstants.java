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
	
	// Interoprable Hazelcast, with non-Java clients
    public static final int MY_DATA_SERIALIZABLE_FACTORY = 1;
    public static final int CLASS_SENTIMENT = 1000;

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
		{ "27th June", "Serbia v Brazil", "#SRBBRA" },
		{ "27th June", "South Korea v Germany", "#KORGER" },
		{ "27th June", "Mexico v Sweden", "#MEXSWE" },
		{ "27th June", "Switzerland v Costa Rica", "#SWICRC" },
		{ "28th June", "England v Belgium", "#ENGBEL" },
		{ "28th June", "Panama v Tunusia", "#PANTUN" },
		{ "28th June", "Japan v Poland", "#JAPPOL" },
		{ "28th June", "Senegal v Colombia", "#SENCOL" },
		{ "2nd July", "Belgium v Japan", "#BELJAP" },
		{ "3rd July", "Colombia v England", "#COLENG" },
		{ "6th July", "Uruguay v France", "#URUFRA" },
		{ "6th July", "Brazil v Belgium", "#BRABEL" },
		{ "7th July", "Sweden v England", "#SWEENG" },
		{ "7th July", "Russia v Croatia", "#RUSCRO" },
		{ "10th July", "France v Belgium", "#FRABEL" },
		{ "11th July", "Croatia v England", "#CROENG" },
		{ "14th July", "Belgium v England", "#BELENG" },
		{ "15th July", "France v Croatia", "#FRACRO" },
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
