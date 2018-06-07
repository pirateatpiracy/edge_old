package book.ch03.drpc;

import java.security.InvalidParameterException;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class AdderBolt<String> extends BaseBasicBolt{

	private static final Object NULL = "NULL";
	private OutputCollector collector;

	
	public void execute(Tuple input, BasicOutputCollector collector) {
		//Parse the add expression
		String[] numbers = (String[]) input.getString(1).split("\\+");
		Integer added = 0;
		try{
			if(numbers.length<2){
				throw new InvalidParameterException("Should be at least 2 numbers");
			}
			for(String num : numbers){
				//Add each member
				added += Integer.parseInt((java.lang.String) num);
			}
		}catch(Exception e){
			//On error emit null
			collector.emit(new Values(input.getValue(0),NULL));
		}
		//Emit the result
		collector.emit(new Values(input.getValue(0),added));
	}

	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("id","result"));
	}
}
