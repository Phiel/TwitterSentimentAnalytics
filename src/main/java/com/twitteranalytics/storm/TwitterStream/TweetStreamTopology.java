package com.twitteranalytics.storm.TwitterStream;

import twitter4j.Status;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class TweetStreamTopology {
	public static void main(String args[]){
		TopologyBuilder tbuilder = new TopologyBuilder();
		tbuilder.setSpout("TweetStreamSpout", new TweetStreamSpout(),1);
		tbuilder.setBolt("ParseTweetBolt", new ParseTweetBolt(), 5).shuffleGrouping("TweetStreamSpout");
		//tbuilder.setBolt("ParseTweetBolt", new ParseTweetBolt(), 5).fieldsGrouping("TweetStreamSpout", new Fields("Tweet") );
		//tbuilder.setBolt("SentimentAnalyzerBolt", new SentimentAnalyzerBolt(), 5);
		Config conf = new Config();
		conf.setDebug(true);
	    conf.registerSerialization(Status.class);
	    
		LocalCluster cluster = new LocalCluster();
	    cluster.submitTopology("TwitterAnalytics", conf, tbuilder.createTopology());
	    
	}
}
