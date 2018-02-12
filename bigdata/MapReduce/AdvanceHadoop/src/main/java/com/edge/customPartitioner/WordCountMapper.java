package com.edge.customPartitioner;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	

	private final Text key =  new Text();    	
	private IntWritable val  =  new IntWritable(1);	
    protected void map(LongWritable bos, Text line, Context context) throws IOException, InterruptedException {    	
    	StringTokenizer st=new StringTokenizer(line.toString());
    	while (st.hasMoreTokens()){ 
    		key.set(st.nextToken());
    		context.write(key, val);
    	}    	
    }
}