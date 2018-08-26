/**
 * The primary goal of combiners is to optimize/minimize the number of key value pairs that will
 *  be shuffled across the network between mappers and reducers and thus to save as most
 *  bandwidth as possible.
 * The thumb rule of combiner is it has to have the same input and output variable types, the reason 
 * for this is combiner use is not guaranteed, it can or can not be used , depending the volume 
 * and number of spills.
 * The reducer can be used as a combiner when it satisfies this rule i.e. same input and output 
 * variable type.
 * The other most important rule for combiner is it can only be used when the function you want 
 * to apply is both commutative and associative. like adding numbers .But not in case like average
 * (while using the same code as reducer).  
 * 
 * 
 * 
 * 
 */

package com.edge.combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



/**
 * @author New
 *
 */
public class Driver1 {
	
public static void main(String[] args) throws Exception { 
    	
    	Configuration conf = new Configuration();
		if (args.length != 2) {
			System.err.println("Usage: SecondarySort <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "CustomCobiner");
		job.setJarByClass(Driver1.class);
		job.setMapperClass(Map.class);
		job.setCombinerClass(Combine.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Average.class);
		job.setReducerClass(Reduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}