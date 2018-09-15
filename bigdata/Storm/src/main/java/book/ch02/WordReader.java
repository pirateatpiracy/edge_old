package book.ch02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

@SuppressWarnings("serial")
public class WordReader implements IRichSpout {

	private SpoutOutputCollector collector;
	private FileReader fileReader;
	private boolean readed = false;
	public boolean isDistributed() {return false;}
	public void ack(Object msgId) {}
	public void close() {}
	public void fail(Object msgId) {}

	/**
	 * The only thing that the methods will do It is emit each 
	 * file line
	 */
	public void nextTuple() {
		/**
		 * The nextuple it is called forever, so if we have been readed the file
		 * we will wait and then return
		 */
		if(readed){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//Do nothing
			}
			return;
		}
		String str;
		//Open the reader
		BufferedReader reader = new BufferedReader(fileReader);
		try{
			//Read all lines
			while((str = reader.readLine()) != null){
				/**
				 * By each line emmit a new value with the line as a their
				 */
				this.collector.emit(new Values(str));
			}
		}catch(Exception e){
			throw new RuntimeException("Error reading tuple",e);
		}finally{
			readed = true;
		}
	}

	/**
	 * We will create the file and get the collector object
	 */
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		try {
			this.fileReader = new FileReader(conf.get("wordsFile").toString());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error reading file ["+conf.get("wordFile")+"]");
		}
		this.collector = collector;
	}

	/**
	 * Declare the output field "word"
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}
	public void activate() {
		// TODO Auto-generated method stub
		
	}
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
}