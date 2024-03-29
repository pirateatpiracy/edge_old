package oldapi;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class StationPartitioner implements Partitioner<LongWritable, Text> {
  
  private NcdcRecordParser parser = new NcdcRecordParser();
  
  
  public int getPartition(LongWritable key, Text value, int numPartitions) {
    parser.parse(value);
    return getPartition(parser.getStationId());
  }

  private int getPartition(String stationId) {
    return 0;
  }

  
  public void configure(JobConf conf) { }
}
