package book.ch04.banktransactions;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * To run this topology you should execute this main as: 
 * java -cp theGeneratedJar.jar book.ch04.banktransactions.Topology
 *
 * @author StormBook
 *
 */
public class Topology {

	public static void main(String[] args) throws InterruptedException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("transactions-spout", new TransactionsSpouts());
		builder.setBolt("random-failure-bolt", new RandomFailureBolt()).
			shuffleGrouping("transactions-spout");
		
		LocalCluster cluster = new LocalCluster();
		Config conf = new Config();
		conf.setDebug(true);
		cluster.submitTopology("transactions-test", conf, builder.createTopology());
		while(true){
			//Will wait for a fail
			Thread.sleep(1000);
		}
	}
}
