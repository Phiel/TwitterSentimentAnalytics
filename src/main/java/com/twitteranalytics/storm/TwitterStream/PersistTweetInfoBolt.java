package com.twitteranalytics.storm.TwitterStream;

import java.util.Map;

import com.twitteranalytics.ModelClasses.TweetInfo;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class PersistTweetInfoBolt extends BaseRichBolt{
	OutputCollector collector=null;
	TweetInfo tweet_info=new TweetInfo();

	public void execute(Tuple tuple) {
		tweet_info=(TweetInfo)tuple.getValueByField("TweetInfo");
	}

	public void prepare(Map map, TopologyContext tc, OutputCollector oc) {
		collector=oc;
	}

	public void declareOutputFields(OutputFieldsDeclarer ofd) {
	}

}

