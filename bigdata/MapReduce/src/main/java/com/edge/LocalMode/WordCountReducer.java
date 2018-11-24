package com.edge.LocalMode;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private IntWritable sum1 = new IntWritable();

	protected void reduce(Text k3, Iterable<IntWritable> v3, Context context)
			throws IOException, InterruptedException {

		int sum = 0;
		for (IntWritable value : v3) {
			sum += value.get();
		}
		sum1.set(sum);
		context.write(k3, sum1);
	}

}