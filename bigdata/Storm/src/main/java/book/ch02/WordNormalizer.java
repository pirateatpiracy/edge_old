package book.ch02;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class WordNormalizer implements IRichBolt {

	private OutputCollector collector;

	public void cleanup() {}

	/**
	 * The bolt will receive the line from the
	 * words file and process it to Normalize this line
	 * 
	 * The normalize will be put the words in lower case
	 * and split the line to get all words in this 
	 */
	public void execute(Tuple input) {
		String word = input.getString(0);
		word = word.trim();
		word = word.toLowerCase();
		String[] words = word.split(" ");
		for(String str : words){
			str = str.trim();
			if(!"".equals(str)){
				//Emit the word
				collector.emit(new Values(str));
			}
		}
		//Set the tuple as Acknowledge
		collector.ack(input);
	}

	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * The bolt will only emit the field "word" 
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}