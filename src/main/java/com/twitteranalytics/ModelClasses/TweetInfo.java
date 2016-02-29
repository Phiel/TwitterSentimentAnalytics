package com.twitteranalytics.ModelClasses;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import edu.stanford.nlp.io.EncodingPrintWriter.out;
import twitter4j.Status;

public class TweetInfo implements Serializable{
	private long TweetId;
	private long UserId;
	private String tweet;
	private double latitude;
	private double longitude;
	private Date created;
	private Set<String> hashtags;
	private double sentiment_score=0.0;
	private String tags;
	private Iterator itr;
	
	
	public TweetInfo() {
		super();
	}


	public TweetInfo(long tweetId, long userId, String tweet, double latitude,
			double longitude, Date created, Set<String> hashtags) {
		super();
		TweetId = tweetId;
		UserId = userId;
		this.tweet = tweet;
		this.latitude = latitude;
		this.longitude = longitude;
		this.created = created;
		this.hashtags = hashtags;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{TweetId=" + TweetId + ", UserId=" + UserId
				+ ", tweet='" + tweet + "', latitude=" + latitude
				+ ", longitude=" + longitude + ", created=" + created
				+ ", hashtags=" + this.getHashtags() + ", sentiment_score="
				+ sentiment_score + "}";
	}
	
	public TweetInfo parseTweetInfo(Status Tweet){
		
		this.TweetId=Tweet.getId();
		this.UserId=Tweet.getUser().getId();
		this.tweet=Tweet.getText();
		this.latitude=Tweet.getGeoLocation().getLatitude();
		this.longitude=Tweet.getGeoLocation().getLongitude();
		this.created=Tweet.getCreatedAt();
		this.tags=Tweet.getHashtagEntities().toString();
		
		return this;
	} 

	/**
	 * @return the tweetId
	 */
	public long getTweetId() {
		return TweetId;
	}
	/**
	 * @param tweetId the tweetId to set
	 */
	public void setTweetId(long tweetId) {
		TweetId = tweetId;
	}
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return UserId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		UserId = userId;
	}
	/**
	 * @return the tweet
	 */
	public String getTweet() {
		return tweet;
	}
	/**
	 * @param tweet the tweet to set
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * @return the hashtags
	 */
	public Set<String> getHashtagSet() {
		return hashtags;
	}
	
	public String getHashtags() {
		if(hashtags.size()==0)
			return "[]";
		
		itr=hashtags.iterator();
		tags="[";
		while(itr.hasNext()){
			if(tags.equalsIgnoreCase("["))
				tags=tags+"'"+itr.next();
			else
				tags=tags+"', '"+itr.next();
		}
		tags=tags+"']";
		return tags;
	}

	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(Set<String> hashtags) {
		this.hashtags = hashtags;
	}
	/**
	 * @return the sentiment_score
	 */
	public double getSentiment_score() {
		return sentiment_score;
	}
	/**
	 * @param sentiment_score the sentiment_score to set
	 */
	public void setSentiment_score(double sentiment_score) {
		this.sentiment_score = sentiment_score;
	}
	
}