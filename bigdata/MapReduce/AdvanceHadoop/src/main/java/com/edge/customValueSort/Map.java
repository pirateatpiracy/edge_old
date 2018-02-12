package com.edge.customValueSort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Employee, Text>{
	
	String [] row;
	Text value =new Text();  ;
	protected void map(LongWritable offSet, Text line, Context context) throws IOException, InterruptedException {
		row=line.toString().split("~");
		value.set(row[4].toString() + "\t"+ row[5].toString() + "\t" + row[6].toString());
		context.write(new Employee(row[0].toString(),	(row[1].toString() + "\t"+ row[2].toString() + "\t" + row[3].toString())),value);
		//value.set(row[1] + "\t"+ row[2] + "\t" + row[3]);
		//context.write(new Employee(row[0],	(row[1] + "\t"+ row[2] + "\t" + row[3])),value);
	}
	
}
