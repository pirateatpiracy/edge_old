package book.ch02;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;



public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
        
        //Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReader());
		builder.setBolt("word-normalizer", new WordNormalizer()).shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter()).shuffleGrouping("word-normalizer");
		
        //Configuration
		Config conf = new Config();
		conf.put("wordsFile", args[0]);
		conf.setDebug(true);
		
        //Topology run
		LocalCluster cluster = new LocalCluster();
		StormTopology topology = builder.createTopology();
		cluster.submitTopology("Getting-Started-Toplogie", conf, topology);
		Thread.sleep(2000);
		cluster.shutdown();
	}
}