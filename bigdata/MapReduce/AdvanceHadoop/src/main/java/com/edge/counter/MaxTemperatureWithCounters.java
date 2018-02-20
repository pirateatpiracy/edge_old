package com.edge.counter;

import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.PropertyConfigurator;
import com.edge.fileMerge.sequenceFile.JobBuilder;
import com.edge.multipleOutputFile.NcdcRecordParser;

public class MaxTemperatureWithCounters extends Configured implements Tool {
	public int run(String[] args) throws Exception {
		Job job = JobBuilder.parseInputAndOutput(this, getConf(), args);
		if (job == null) {
			return -1;
		}

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapperClass(MaxTemperatureMapperWithCounters.class);
		job.setCombinerClass(MaxTemperatureReducer.class);
		job.setReducerClass(MaxTemperatureReducer.class);
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		int exitCode = ToolRunner.run(new MaxTemperatureWithCounters(), args);
		System.exit(exitCode);
	}
}

class MaxTemperatureMapperWithCounters extends Mapper<LongWritable, Text, Text, IntWritable> {
	enum Temperature {
		MISSING, MALFORMED
	}
	private NcdcRecordParser parser = new NcdcRecordParser();

	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		parser.parse(value);
		if (parser.isValidTemperature()) {
			int airTemperature = parser.getAirTemperature();
			context.write(new Text(parser.getYear()), new IntWritable(airTemperature));
		} else if (parser.isMalformedTemperature()) {
			System.err.println("Ignoring	possibly	corrupt	input:	" + value);
			context.getCounter(Temperature.MALFORMED).increment(1);
		} else if (parser.isMissingTemperature()) {
			context.getCounter(Temperature.MISSING).increment(1);
		}
		// dynamic counter
		context.getCounter("TemperatureQuality", parser.getQuality()).increment(1);
	}
}

class MaxTemperatureReducer
extends Reducer<Text, IntWritable, Text, IntWritable> {


public void reduce(Text key, Iterable<IntWritable> values,
    Context context)
    throws IOException, InterruptedException {
  
  int maxValue = Integer.MIN_VALUE;
  for (IntWritable value : values) {
    maxValue = Math.max(maxValue, value.get());
  }
  context.write(key, new IntWritable(maxValue));
}
}