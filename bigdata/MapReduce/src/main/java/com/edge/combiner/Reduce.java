package com.edge.combiner;

import java.io.IOException;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, Average, Text, LongWritable> {
	LongWritable avg =new LongWritable();
	protected void reduce(Text key, Iterable<Average> val, Context context)throws IOException, InterruptedException {
		int total=0; int count=0; long avgg=0;
		
		for (Average value : val){
			total+=value.number*value.count;
			count+=value.count;
			avgg=total/count;	
			}
		avg.set(avgg);
			context.write(key, avg);
	}
}
