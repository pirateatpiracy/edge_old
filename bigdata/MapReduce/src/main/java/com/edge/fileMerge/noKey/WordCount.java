package com.edge.fileMerge.noKey;

import java.io.IOException;
import java.net.URI;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.PropertyConfigurator;

public class WordCount extends Configured implements Tool {
	 public static void main(String args[]) throws Exception {
		 String log4jConfPath = "log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
		    int res = ToolRunner.run(new WordCount(), args);
		    System.exit(res);
		  }

	  public int run(String[] args) throws Exception {    	
    	Configuration conf = new Configuration();
    	
    	
		Job job = new Job(conf, "word count");
		job.setJarByClass(WordCount.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		Path outputPath = new Path(args[1]);
		FileSystem fs = FileSystem.get(new URI(outputPath.toString()), conf);
		fs.delete(outputPath);
		FileOutputFormat.setOutputPath(job, outputPath);
		return job.waitForCompletion(true) ? 0 : 1;
    }
    
}


class WordCountMapper extends Mapper<LongWritable, Text, Text, NullWritable> {	
    protected void map(LongWritable k1, Text v1, Context context) throws IOException, InterruptedException { 
    		context.write( v1,NullWritable.get());    	   	
    }
}



class WordCountReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
	protected void reduce(Text k3, Iterable<Text> v3, Context context)
			throws IOException, InterruptedException {
		for (Text value : v3) {
			context.write(k3, NullWritable.get());
		}
	}

}