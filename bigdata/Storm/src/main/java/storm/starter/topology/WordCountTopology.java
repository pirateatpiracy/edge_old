package storm.starter.topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.ShellBolt;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
//import storm.starter.spout.RandomSentenceSpout;
import org.apache.storm.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This topology demonstrates Storm's stream groupings and multilang capabilities.
 */
//storm.starter.topology.WordCountTopology
public class WordCountTopology {

  public static void main(String[] args) throws Exception {

    TopologyBuilder builder = new TopologyBuilder();

    builder.setSpout("spout", new RandomSentenceSpout(), 5);

    builder.setBolt("split", new SplitSentence(), 8).shuffleGrouping("spout");
    builder.setBolt("count", new WordCount(), 12).fieldsGrouping("split", new Fields("word"));

    Config conf = new Config();
    conf.setDebug(false);

    if (args != null && args.length > 0) {
      conf.setNumWorkers(3);

      StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
    }
    else {
      conf.setMaxTaskParallelism(3);

      LocalCluster cluster = new LocalCluster();
      cluster.submitTopology("word-count", conf, builder.createTopology());

      Thread.sleep(100000);

      cluster.shutdown();
    }
  }
}


class RandomSentenceSpout extends BaseRichSpout {
	  SpoutOutputCollector _collector;
	  Random _rand;


	  
	  public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
	    _collector = collector;
	    _rand = new Random();
	  }

	  
	  public void nextTuple() {
	    Utils.sleep(10);
	    String[] sentences = new String[]{ "the cow jumped over the moon", "an apple a day keeps the doctor away",
	        "four score and seven years ago", "snow white and the seven dwarfs", "i am at two with nature" };
	    String sentence = sentences[_rand.nextInt(sentences.length)];
	    _collector.emit(new Values(sentence));
	  }

	  
	  public void ack(Object id) {
	  }

	  
	  public void fail(Object id) {
	  }

	  
	  public void declareOutputFields(OutputFieldsDeclarer declarer) {
	    declarer.declare(new Fields("word"));
	  }

	}

 
 class SplitSentence extends ShellBolt implements IRichBolt {

	 private OutputCollector collector;


	 public void prepare(Map conf,  TopologyContext context, OutputCollector collector) {
	    this.collector = collector;
	 }

	   /* public SplitSentence() {
	      super("python", "splitsentence.py");
	    }*/
	    public void execute(Tuple tuple) {
	    	String [] sntnc = tuple.getString(0).split(" ");
	       for(String word: sntnc)
	        collector.emit(new Values(word));
	     }
	    
	    public void declareOutputFields(OutputFieldsDeclarer declarer) {
	      declarer.declare(new Fields("word"));
	    }

	    
	    public Map<String, Object> getComponentConfiguration() {
	      return null;
	    }
	  }
 
 class WordCount extends BaseBasicBolt {
	    Map<String, Integer> counts = new HashMap<String, Integer>();

	    
	    public void execute(Tuple tuple, BasicOutputCollector collector) {
	      String word = tuple.getString(0);
	      Integer count = counts.get(word);
	      if (count == null)
	        count = 0;
	      count++;
	      counts.put(word, count);
	      collector.emit(new Values(word, count));
	    }
	    public void cleanup() {
	        for(Map.Entry<String, Integer> entry:counts.entrySet())
	           System.out.println(entry.getKey()+" :**: " + entry.getValue());
	        }
	    
	    public void declareOutputFields(OutputFieldsDeclarer declarer) {
	      declarer.declare(new Fields("word", "count"));
	    }
	  }
	 