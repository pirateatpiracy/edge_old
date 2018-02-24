package com.edge.udf.udaf;

/*
 * hive>select emp_no ,avg(salary) from salaries group by emp_no limit 10<Built-In-Function>;
 * export as case.jar
 * hive> ADD JAR case.jar;
 * hive> CREATE TEMPORARY FUNCTION average  as 'com.edge.udaf.Average';
 * hive> select emp_no ,average(salary) from salaries group by emp_no limit 10;
*/

/*
 * public void init():when extending UDAFEvaluator class, we need to implement an init method that is responsible for resetting variables so that we can 
 * handle a new group. Whenever hive observes a new group key, it will call the init method so to make sure that all the variables are reinitialized.
 
 * public boolean iterate([list of arguments]):  hive calls iterate function of the UDAFEvaluator class and pass all the variables from the SQL query as
 *  arguments to the iterate method. The iterate method is then responsible for increment the variables.

 * public [RETURN TYPE] terminate():  terminate function is responsible for the returning the aggregate value i.e. average in the above example.
 *  After the last record of a group has been sent to the UDAFEvaluator, hive will call the terminate function and grab its output.
 
 * public [INTERMEDIATE RETURN TYPE] terminatePartial() :optimization that enables map side aggregation (or usage of combiner).
 * The main bottleneck in hadoop is transferring data from mapper to reducer. terminatePartial and merge address this by doing partial aggregation at the mapper side.
 * when computing average home price rather than sending all the records related to a particular zipcode to a reducer we can use combiner to calculate total value and cnt and send these two values to the reducer.
 * It is responsible for returning the total_value and cnt.
  
 * public void merge([INTERMEDIATE RETURN TYPE]).On the reducer side we need a method to handle the partial computation which is done by merge method.
 *
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
import org.apache.hadoop.hive.ql.metadata.HiveException;

public class Average extends UDAF {
	static final Log LOG = LogFactory.getLog(Average.class.getName());

	public static class AverageUDAFEvaluator implements UDAFEvaluator {

		public static class Item {//Class to be returned.Use item class to serialize intermediate computation
			double total_value = 0;
			int cnt = 0;
		}

		private Item item = null;//object to be returned.
		
		public AverageUDAFEvaluator() {// Constructor
			super();
			init();
		}

		public void init() {// function: init() Its called before records pertaining to a new group are streamed.
			LOG.debug("======== init ========");
			item = new Item();
		}
	 
		public boolean iterate(double value) throws HiveException {//function: iterate This function is called for every individual record of a group.	
			LOG.debug("======== iterate ========");
			if (item == null)
				throw new HiveException("Item is not initialized");
			item.total_value = item.total_value + value;
			item.cnt = item.cnt + 1;
			return true;
		}

		public double terminate() {// function: terminate this function is called after the last record of the group has been streamed
			LOG.debug("======== terminate ========");
			return item.total_value / item.cnt;
		}
		
		public Item terminatePartial() {// function: terminatePartial this function is called on the mapper side and  returns partially aggregated results.
			LOG.debug("======== terminatePartial ========");
			return item;
		}

		public boolean merge(Item another) {// function: merge This function is called to merge two partially aggregated.
			LOG.debug("======== merge ========");
			if (another == null)
				return true;
			item.total_value += another.total_value;
			item.cnt += another.cnt;
			return true;
		}
	}
}