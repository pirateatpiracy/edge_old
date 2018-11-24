package com.edge.combiner;

import java.io.IOException;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Text, Average> {

	Text name = new Text();
	String[] row;
	
	protected void map(LongWritable offSet, Text line, Context context)	throws IOException, InterruptedException {
		row = line.toString().split(" ");
		System.out.println("Key "+row[0]+"Value "+row[1]);
		name.set(row[0]);
		context.write(name, new Average(Integer.parseInt(row[1].toString()), 1));
	}

}
