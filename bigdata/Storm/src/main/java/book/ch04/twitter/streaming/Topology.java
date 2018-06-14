package book.ch04.twitter.streaming;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * To run this topology you should execute this main as: 
 * java -cp theGeneratedJar.jar twitter.streaming.Topology <track> <twitterUser> <twitterPassword>
 *
 * @author StormBook
 *
 */
public class Topology {
 
	public static void main(String[] args) throws InterruptedException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("tweets-collector", new ApiStreamingSpout(),1);
		builder.setBolt("hashtag-sumarizer", new TwitterSumarizeHashtags()).
			shuffleGrouping("tweets-collector"); 
		
		LocalCluster cluster = new LocalCluster();
		Config conf = new Config();
		conf.put("track", args[0]);
		conf.put("user", args[1]);
		conf.put("password", args[2]);
		
		cluster.submitTopology("twitter-test", conf, builder.createTopology());
	}
}
