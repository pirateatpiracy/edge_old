package com.edge.filter;

/*
 * @author : edge
 * PageFilter: This filter takes one argument – a page size. It returns page size number of rows from the table.
*/
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.PropertyConfigurator;

public class Page {

	// vv PageFilterExample
	private static final byte[] POSTFIX = new byte[] { 0x00 };
	// ^^ PageFilterExample

	public static void main(String[] args) throws IOException {

		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

		Configuration conf = HBaseConfiguration.create();
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf("emp_hive"));

		Filter filter = new PageFilter(15);
		int totalRows = 0;
		Scan scan = new Scan();
		scan.setFilter(filter);
		ResultScanner scanner = table.getScanner(scan);
		for (Result result : scanner) {
			System.out.println(++totalRows + ": " + result);
		}
		scanner.close();

		System.out.println("total rows: " + totalRows);
	}
}
