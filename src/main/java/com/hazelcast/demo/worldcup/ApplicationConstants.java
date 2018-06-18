package com.hazelcast.demo.worldcup;

/**
 * <p>
 * Useful constants.
 * </p>
 */
public class ApplicationConstants {

	// Hazelcast
	public static final String[] IMAP_NAMES = { };
	public static final String MULTIMAP_NAME_GAMES = "games";
	public static final String[] MULTIIMAP_NAMES = { MULTIMAP_NAME_GAMES };

	/* "Recommended" country trios:
	 * 
	 * AUS - Australia
	 * BEL - Belgium
	 * BRA - Brazil
	 * COL - Colombia
	 * CRC - Costa Rica
	 * CRO - Croatia
	 * DEN - Denmark
	 * EGY - Egypt
	 * ENG - England
	 * ESP - Spain
	 * FRA - France
	 * GER - Germany
	 * IRN - Iran
	 * ISL - Iceland
	 * JPN - Japan
	 * KOR - South Korea
	 * KSA - Saudi Arabia
	 * MEX - Mexico
	 * MAR - Morocco
	 * NGA - Nigeria
	 * PAN - Panama
	 * PER - Peru
	 * POL - Poland
	 * POR - Portugal
	 * RUS - Russia
	 * SEN - Senegal
	 * SRB - Serbia
	 * SUI - Switzerland
	 * SWE - Sweden
	 * TUN - Tunisia
	 * URU - Uruguay
	 */
	
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
}
