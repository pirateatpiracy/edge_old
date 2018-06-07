package book.ch03.countword;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class SignalsSpout extends BaseRichSpout{

	private SpoutOutputCollector collector;


	
	public void nextTuple() {
		collector.emit("signals",new Values("refreshCache"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}

	
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		this.collector = collector;
	}

	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream("signals",new Fields("action"));
	}

}
