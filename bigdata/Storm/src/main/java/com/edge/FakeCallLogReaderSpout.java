package com.edge;

import java.util.*;
//import storm tuple packages
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

//import Spout interface packages
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;

//Create a class FakeLogReaderSpout which implement IRichSpout interface  to access functionalities
	
public class FakeCallLogReaderSpout implements IRichSpout {
 //Create instance for SpoutOutputCollector which passes tuples to bolt.
 private SpoutOutputCollector collector;
 private boolean completed = false;
	
 //Create instance for TopologyContext which contains topology data.
 private TopologyContext context;
	
 //Create instance for Random class.
 private Random randomGenerator = new Random();
 private Integer idx = 0;

 
 public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
    this.context = context;
    this.collector = collector;
 }

 
 public void nextTuple() {
    if(this.idx <= 1000) {
       List<String> mobileNumbers = new ArrayList<String>();
       mobileNumbers.add("1234123401");
       mobileNumbers.add("1234123402");
       mobileNumbers.add("1234123403");
       mobileNumbers.add("1234123404");

       Integer localIdx = 0;
       while(localIdx++ < 100 && this.idx++ < 1000) {
          String fromMobileNumber = mobileNumbers.get(randomGenerator.nextInt(4));
          String toMobileNumber = mobileNumbers.get(randomGenerator.nextInt(4));
				
          while(fromMobileNumber == toMobileNumber) {
             toMobileNumber = mobileNumbers.get(randomGenerator.nextInt(4));
          }
				
          Integer duration = randomGenerator.nextInt(60);
          this.collector.emit(new Values(fromMobileNumber, toMobileNumber, duration));
       }
    }
 }

 
 public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("from", "to", "duration"));
 }

 //Override all the interface methods
 
 public void close() {}

 public boolean isDistributed() {
    return false;
 }

 
 public void activate() {}

  
 public void deactivate() {}

 
 public void ack(Object msgId) {}

 
 public void fail(Object msgId) {}

 
 public Map<String, Object> getComponentConfiguration() {
    return null;
 }
}