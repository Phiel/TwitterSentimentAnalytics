package com.twitteranalytics.spark.TwitterStream;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.Status;
import twitter4j.auth.Authorization;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import com.twitteranalytics.ModelClasses.TwitterOAuth;

public class SparkTwitterStream {


	public static void main(String[] args) {
		
		twitter4j.conf.ConfigurationBuilder builder=new ConfigurationBuilder();
		builder.setOAuthConsumerKey(TwitterOAuth.getConsumer_Key());
		builder.setOAuthConsumerSecret(TwitterOAuth.getConsumer_Secret());
		builder.setOAuthAccessToken(TwitterOAuth.getAccess_Token());
		builder.setOAuthAccessTokenSecret(TwitterOAuth.getAccess_Token_Secret());
		
		Configuration conf= builder.build();
		Authorization tOAuth = new OAuthAuthorization(conf);
		SparkConf sparkconf = new SparkConf().setAppName("Twitter Analytics").setMaster("local[*]");
		JavaStreamingContext jsc=new JavaStreamingContext(sparkconf, new Duration(10000));
		JavaReceiverInputDStream<Status> twitterStream=TwitterUtils.createStream(jsc, tOAuth);

		JavaDStream<Status> location_filter=twitterStream.filter(new Function<Status,Boolean>(){
			GeoLocation location;
			public Boolean call(Status status){
				try{
					if((location=status.getGeoLocation())!=null){
						if(location.getLatitude()>28.54d && location.getLatitude()<48.85d && location.getLongitude()>-127.5d && location.getLongitude()<-55.90)
								return true;
						
						else if(location.getLatitude()>60.00d && location.getLatitude()<70.00d && location.getLongitude()>-165.0d && location.getLongitude()<-142.0d)
								return true;
						else 
								return false;
					}	
					else 
						return false;
				}catch(NullPointerException ex){
					return false;
				}
			}
		});

		JavaDStream<String> statuses=location_filter.map(new Function<Status,String>(){
			
			public String call(Status status){
				try{
					return (status.toString().replaceFirst("StatusJSONImpl", ""));	
				}catch(NullPointerException  e){
					return "Exception Occurred";
				} 
			}	
		});
		
		statuses.print();
		jsc.start();
		jsc.awaitTermination();
	}
}