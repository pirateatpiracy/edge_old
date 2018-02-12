package com.edge.combiner;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Combine extends Reducer<Text, Average, Text, Average>{
	
	protected void reduce(Text name, Iterable<Average> val, Context context)throws IOException, InterruptedException {
		int total=0; int count=0; long avg=0;
		
		for (Average value : val){
			total+=value.number;
			count+=1;
			avg=total/count;	
			}
		context.write(name, new Average(avg, count));
			
	}
}
