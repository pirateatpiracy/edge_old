package com.edge.customKeySort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;



public class InverseKeyComparator extends WritableComparator{

	protected InverseKeyComparator(){super(Text.class, true);}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int compare(WritableComparable w1,WritableComparable w2){
		return w2.compareTo(w1);}
	

}
