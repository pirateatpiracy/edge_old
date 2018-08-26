package com.edge.chainMR;


/*
 * [MAP+ / REDUCE MAP*] 
 * In this method only one job id done with only one reducer.
 * In the Mapper node:  Map-> Map-> Map-> Map*
 * In the Reducer node: Reduce -> Map ->Map*
 * The ChainMapper class allows to use multiple Mapper classes within a single Map task.
 * The Mapper classes are invoked in a chained (or piped) fashion, the output of the first becomes the input of the second, and so on until the last Mapper, the output of the last Mapper will be written to the task's output.
 * The ChainReducer class allows to chain multiple Mapper classes after a Reducer within the Reducer task.
 * For each record output by the Reducer, the Mapper classes are invoked in a chained (or piped) fashion, the output of the first becomes the input of the second, and so on until the last Mapper, the output of the last Mapper will be written to the task's output.
 * 
 */
import java.io.IOException;
import java.net.URI;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount extends Configured implements Tool {
	public static void main(String args[]) throws Exception {
		System.exit(ToolRunner.run(new WordCount(), args));
	}

	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "word count");
		ChainMapper.addMapper(job, WordCountMapper.class, LongWritable.class, Text.class, Text.class, IntWritable.class,new Configuration(false));
		ChainMapper.addMapper(job, UpperCaseWordCountMapper.class, Text.class, IntWritable.class, Text.class,IntWritable.class, new Configuration(false));
		ChainReducer.setReducer(job, WordCountReducer.class, Text.class, IntWritable.class, Text.class,	IntWritable.class, new Configuration(false));
		ChainReducer.addMapper(job, LowerCaseWordCountMapper.class, Text.class, IntWritable.class, Text.class,IntWritable.class, new Configuration(false));
		job.setJarByClass(WordCount.class);
		// job.setMapperClass(WordCountMapper.class);
		// job.setCombinerClass(WordCountReducer.class);
		// job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		Path outputPath = new Path(args[1]);
		FileSystem fs = FileSystem.get(new URI(outputPath.toString()), conf);
		fs.delete(outputPath);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, outputPath);
		return job.waitForCompletion(true) ? 0 : 1;
	}

}

class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final Text k2 = new Text();
	private IntWritable v2 = new IntWritable(1);

	protected void map(LongWritable k1, Text v1, Context context) throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(v1.toString());
		while (st.hasMoreTokens()) {
			k2.set(st.nextToken());
			context.write(k2, v2);
		}
	}
}

class UpperCaseWordCountMapper extends Mapper<Text, IntWritable, Text, IntWritable> {
	public void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
		String val = key.toString().toUpperCase();
		Text newKey = new Text(val);
		context.write(newKey, value);
	}
}

class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable sum1 = new IntWritable();
	protected void reduce(Text k3, Iterable<IntWritable> v3, Context context) throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value : v3) {
			sum += value.get();
		}
		sum1.set(sum);
		context.write(k3, sum1);
	}
}

class LowerCaseWordCountMapper extends Mapper<Text, IntWritable, Text, IntWritable> {
	
	public void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
		String val = key.toString().toLowerCase();
		Text newKey = new Text(val);
		context.write(newKey, value);
	}
}
