package com.twitteranalytics.ModelClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TwitterOAuth {

	private static String Consumer_Key;
	private static String Consumer_Secret;
	private static String Access_Token;
	private static String Access_Token_Secret;
	
	static{
		Properties property= new Properties();
		String filename="resources/config.properties";
		try {
			InputStream is= new FileInputStream(filename);
			property.load(is);
			
			Consumer_Key=property.getProperty("Consumer_Key");
			Consumer_Secret=property.getProperty("Consumer_Secret");
			Access_Token=property.getProperty("Access_Token");
			Access_Token_Secret=property.getProperty("Access_Token_Secret");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(filename+": No such file found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the consumer_Key
	 */
	public static String getConsumer_Key() {
		return Consumer_Key;
	}

	/**
	 * @param consumer_Key the consumer_Key to set
	 */
	public static void setConsumer_Key(String consumer_Key) {
		Consumer_Key = consumer_Key;
	}

	/**
	 * @return the consumer_Secret
	 */
	public static String getConsumer_Secret() {
		return Consumer_Secret;
	}

	/**
	 * @param consumer_Secret the consumer_Secret to set
	 */
	public static void setConsumer_Secret(String consumer_Secret) {
		Consumer_Secret = consumer_Secret;
	}

	/**
	 * @return the access_Token
	 */
	public static String getAccess_Token() {
		return Access_Token;
	}

	/**
	 * @param access_Token the access_Token to set
	 */
	public static void setAccess_Token(String access_Token) {
		Access_Token = access_Token;
	}

	/**
	 * @return the access_Token_Secret
	 */
	public static String getAccess_Token_Secret() {
		return Access_Token_Secret;
	}

	/**
	 * @param access_Token_Secret the access_Token_Secret to set
	 */
	public static void setAccess_Token_Secret(String access_Token_Secret) {
		Access_Token_Secret = access_Token_Secret;
	}
}
