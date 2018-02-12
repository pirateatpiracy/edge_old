package com.edge.customValueSort;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Employee, Text, Employee, Text> {

	protected void reduce(Employee key, Iterable<Text> val, Context context)throws IOException, InterruptedException {

		for (Text value : val)
			context.write(key, value);
	}
}
