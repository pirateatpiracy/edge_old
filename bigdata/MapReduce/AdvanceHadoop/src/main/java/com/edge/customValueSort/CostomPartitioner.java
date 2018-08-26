/**
 * The only reason for this class is not to use the whole key(Employee), but only deptno.
 */
package com.edge.customValueSort;

/**
 * @author New
 *
 */

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class CostomPartitioner extends Partitioner<Employee, Text> {

	
	public int getPartition(Employee key, Text value, int numReduceTasks) {	
		return (key.getDeptNo().hashCode() % numReduceTasks);
	}
}