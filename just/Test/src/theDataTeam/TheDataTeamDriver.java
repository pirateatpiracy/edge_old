package theDataTeam;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat; 


public class TheDataTeamDriver {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		if (args.length != 2) {
			System.err.println("Usage: Input and ouput path needed <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "TheDataTeam");
		job.setJarByClass(TheDataTeamDriver.class);
		job.setMapperClass(TheDataTeamMapper.class);
		job.setNumReduceTasks(1);
		job.setReducerClass(TheDataTeamReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}

class TheDataTeamMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

	private Text k2 = new Text();
	private DoubleWritable v2= new DoubleWritable();
	private String line;
	private String [] strarr;
	private String key;
	private double value;
	//
	protected void map(LongWritable k1, Text v1, Context context)throws IOException, InterruptedException {
		line = v1.toString();
		 strarr=line.split("-");
		 if(strarr[0].equalsIgnoreCase("Jun") && strarr[1].equalsIgnoreCase("2017")){
		 key=strarr[0]+strarr[1]+"-"+strarr[2];
		 value=Double.parseDouble(strarr[3]);		
		k2.set(key);
		v2.set(value);
		context.write(k2, v2);}
	}
	
}

class TheDataTeamReducer extends Reducer< Text,DoubleWritable, Text, NullWritable> { 

	private Text k3 = new Text();
	private DoubleWritable v3=new DoubleWritable();
	double sum;double highest=0;String id="";
	//
	protected void reduce(Text k2, Iterable<DoubleWritable> v2, Context context)
			throws IOException, InterruptedException {
		sum=0;
		for (DoubleWritable V : v2) {
			sum += V.get();
		}
		if(sum>highest) 
			{
			highest=sum;
			id=k2.toString().split("-")[1];			
			k3.set(id);
			v3.set(highest);
			}			
		}
	
	  protected void cleanup(Context context) throws IOException, InterruptedException {
	      context.write(k3, NullWritable.get());
	  }
	}

/*
May-2017-120-245.50
Jun-2017-124-21.50
Jun-2017-110-34.00
Jun-2017-120-200.00
Jul-2017-154-546.50
Jul-2017-110-1500.00
Jun-2017-124-245.50
*/
