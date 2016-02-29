package com.twitteranalytics.storm.TwitterStream;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.twitteranalytics.ModelClasses.NLP;
import com.twitteranalytics.ModelClasses.TweetInfo;

public class SentimentAnalyzerBolt extends BaseRichBolt {

	TweetInfo tweet_info=new TweetInfo();
	OutputCollector collector=null;
	NLP nlp=new NLP();
	
	public void execute(Tuple tuple) {
		tweet_info =(TweetInfo)tuple.getValueByField("TweetInfo");
		tweet_info.setSentiment_score(nlp.computeSentimentScore(tweet_info.getTweet()));
		collector.emit(new Values(tweet_info));
	}

	public void prepare(Map map, TopologyContext tc, OutputCollector oc) {
		collector=oc;
	}

	public void declareOutputFields(OutputFieldsDeclarer ofd) {
		ofd.declare(new Fields("Tweet"));
	}

}
