package com.edge.combiner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class Average implements Writable {
	
	long number;
	int count;

	public Average() {super();}
	
	public Average(long number, int count) {
		this.number = number;
		this.count = count;
	}
	
	public long getNumber() {return number;}
	public void setNumber(long number) {this.number = number;}
	public int getCount() {return count;}
	public void setCount(int count) {this.count = count;}

	
	public void readFields(DataInput dataInput) throws IOException {
		number = WritableUtils.readVLong(dataInput);
		count = WritableUtils.readVInt(dataInput);		
	}

	
	public void write(DataOutput dataOutput) throws IOException {
		WritableUtils.writeVLong(dataOutput, number);
		WritableUtils.writeVInt(dataOutput, count);
		
	}
	

}
