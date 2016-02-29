package com.twitteranalytics.storm.TwitterStream;

import java.util.Map;

import com.twitteranalytics.ModelClasses.TweetInfo;
import com.twitteranalytics.ModelClasses.TwitterUser;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class PersistUserDetailsBolt extends BaseRichBolt{

	OutputCollector collector=null;
	TwitterUser twitter_user=new TwitterUser();

	public void execute(Tuple tuple) {
		twitter_user=(TwitterUser)tuple.getValueByField("TwitterUser");
		
	}

	public void prepare(Map map, TopologyContext tc, OutputCollector oc) {
		collector=oc;
	}

	public void declareOutputFields(OutputFieldsDeclarer ofd) {
	}

}
