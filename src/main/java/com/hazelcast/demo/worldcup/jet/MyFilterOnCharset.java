package com.hazelcast.demo.worldcup.jet;

import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * A filter to indicate if text has only characters we can
 * analyse.
 * </p>
 */
public class MyFilterOnCharset {

	private static CharsetEncoder charsetEncoder
		= StandardCharsets.US_ASCII.newEncoder();
	
    public static boolean westernChars(String s) {
    	return charsetEncoder.canEncode(s);
    }

}
