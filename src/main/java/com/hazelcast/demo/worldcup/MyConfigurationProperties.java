package com.hazelcast.demo.worldcup;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * <p>
 * Properties to load from the "{@code application.yml}" file, the
 * hashtag to search for on Twitter and the four paramters to make
 * a client connection to Twitter.
 * </p>
 * <p>The Maven build process will replace the placeholder in the
 * "{@code application.yml}" with the property defined in the 
 * "{@code pom.xml}". This is a little better than hard-coding it.
 * </p>
 */
@Configuration
@ConfigurationProperties("my")
@Data
@Validated
public class MyConfigurationProperties {

	public static final String CONSUMER_KEY = "consumerKey";
	public static final String CONSUMER_SECRET = "consumerSecret";
	public static final String TOKEN = "token";
	public static final String TOKEN_SECRET = "tokenSecret";

	@NotNull
	private String hashtag;

	@NotNull
	private String consumerKey;
	@NotNull
	private String consumerSecret;
	@NotNull
	private String token;
	@NotNull
	private String tokenSecret;

}
