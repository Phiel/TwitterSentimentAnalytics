package com.twitteranalytics.storm.TwitterStream;

import java.util.Map;
import twitter4j.GeoLocation;
import twitter4j.Status;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.twitteranalytics.ModelClasses.TweetInfo;
import com.twitteranalytics.ModelClasses.TwitterUser;

public class ParseTweetBolt extends BaseRichBolt{
	
	Status Tweet=null;
	GeoLocation location=null;
	OutputCollector collector=null;
	TweetInfo tweetInfo = new TweetInfo();
	TwitterUser twitter_user=new TwitterUser();
	int i;
	
	
	public void execute(Tuple tuple) {
			Tweet = (Status) tuple.getValue(0);
			collector.ack(tuple);
			
			tweetInfo = tweetInfo.parseTweetInfo(Tweet);
			System.out.println(tweetInfo);
			twitter_user=twitter_user.parseUserDetails(Tweet.getUser());
			System.out.println(twitter_user);
			
			//Emit new Values from this bolt to the next Bolts
			collector.emit(new Values(tweetInfo,twitter_user));
	}
	
	public void prepare(Map map, TopologyContext tc, OutputCollector oc) {
	    collector = oc;
	}

	public void declareOutputFields(OutputFieldsDeclarer ofd) {
		ofd.declare(new Fields("TweetInfo","TwitterUser"));
	}
}