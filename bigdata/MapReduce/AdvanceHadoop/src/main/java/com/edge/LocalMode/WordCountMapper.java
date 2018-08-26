package com.edge.LocalMode;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	

	private final Text k2 =  new Text();    	
	private IntWritable v2  =  new IntWritable(1);	
    protected void map(LongWritable k1, Text v1, Context context) throws IOException, InterruptedException {    	
    	StringTokenizer st=new StringTokenizer(v1.toString());
    	while (st.hasMoreTokens()){ 
    		k2.set(st.nextToken());
    		context.write(k2, v2);
    	}    	
    }
}