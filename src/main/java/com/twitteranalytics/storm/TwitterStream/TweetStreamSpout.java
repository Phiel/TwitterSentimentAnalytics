package com.twitteranalytics.storm.TwitterStream;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import backtype.storm.Config;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import com.twitteranalytics.ModelClasses.TwitterOAuth;

public class TweetStreamSpout extends BaseRichSpout{

	private static final long serialVersionUID = -4161268927192522338L;
	
	SpoutOutputCollector collector;
	TwitterStream twitterStream;
	LinkedBlockingQueue<Status> queue = null;
	GeoLocation location;
	//Declaring Inner Class

	class TweetListener implements StatusListener{

		public void onException(Exception ex) {
			ex.printStackTrace();
		}

		public void onDeletionNotice(StatusDeletionNotice arg0) {
		}

		public void onScrubGeo(long arg0, long arg1) {
		}

		public void onStallWarning(StallWarning arg0) {
		}

		public void onStatus(Status status) {
		    try{
		    	location=status.getGeoLocation();
		    	System.out.println(location.getLatitude()+" ; "+location.getLongitude());
				if(location!=null){
					
					//for whole United States of America
					//Also covers small parts of Mexico, Canada
					if(location.getLatitude()>28.54d && location.getLatitude()<48.85d && location.getLongitude()>-127.5d && location.getLongitude()<-55.90)
						queue.offer(status);	
										
					// This conditional statement is for Alaska
					else if(location.getLatitude()>60.00d && location.getLatitude()<70.00d && location.getLongitude()>-165.0d && location.getLongitude()<-142.0d)
						queue.offer(status);
				}
		    }catch(NullPointerException ex){
		    }
		}

		public void onTrackLimitationNotice(int arg0) {
		}
	};  
	// End of Inner Class
	
	public void close() {
	    twitterStream.shutdown();
	}

	public void nextTuple() {
	    // Pick a tweet from the buffer
	    Status tweet = queue.poll();
	    
	    // If no tweet is available, wait for 10 ms and return
	    if (tweet==null) 
	    {
	      Utils.sleep(50);
	      return;
	    }
	    // Emit the tweet to next stage bolt
	    collector.emit(new Values(tweet));
	}

	public void open(Map map, TopologyContext tc, SpoutOutputCollector soc) {

		queue = new LinkedBlockingQueue<Status>(1000);
	    collector = soc;


	    // build the config with credentials for twitter 4j
	    ConfigurationBuilder config =new ConfigurationBuilder()
	               .setOAuthConsumerKey(TwitterOAuth.getConsumer_Key())
	               .setOAuthConsumerSecret(TwitterOAuth.getConsumer_Secret())
	               .setOAuthAccessToken(TwitterOAuth.getAccess_Token())
	               .setOAuthAccessTokenSecret(TwitterOAuth.getAccess_Token_Secret());

	    // create the twitter stream factory with the config
	    TwitterStreamFactory tsfactory = new TwitterStreamFactory(config.build());

	    // get an instance of twitter stream
	    twitterStream = tsfactory.getInstance();

	    // provide the handler for twitter stream
	    twitterStream.addListener(new TweetListener());
        FilterQuery locationFilter = new FilterQuery();
        double[][] locations = {{-180.0d,28.0d},{-54.0d,90.0d}};
        locationFilter.locations(locations);
        twitterStream.filter(locationFilter);
	}

	public void declareOutputFields(OutputFieldsDeclarer ofd) {
		ofd.declare(new Fields("Tweet"));
	}

	public Map<String, Object> getComponentConfiguration() {
	    Config ret = new Config();
	    ret.setMaxTaskParallelism(1);
	    return ret;
	}
}