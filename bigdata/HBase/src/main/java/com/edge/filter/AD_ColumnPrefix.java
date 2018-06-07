package com.edge.filter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.PropertyConfigurator;

public class AD_ColumnPrefix {
	public static void main(String[] args) throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Configuration conf = HBaseConfiguration.create();
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf("emp_hive"));
		Scan scan = new Scan();
		//Using Filter
		Filter filter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("first_name")));
		Filter filter2 = new  ColumnPrefixFilter( Bytes.toBytes("first_n"));
		scan.setStartRow(Bytes.toBytes("10012"));
	    scan.setStopRow(Bytes.toBytes("10015"));
		scan.setFilter(filter2);
		ResultScanner scanner = table.getScanner(scan);
		int rowCount = 0;
		for (Result result : scanner) {
			for (Cell cell : result.rawCells()) {
				System.out.println("Cell: " + cell + ", Value: "
						+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
			}
			rowCount++;
		}
		System.out.println("Total row found: " + rowCount);
		
		

		
		
		//Using only scan without filter.
/*		Scan scan2 = new Scan();
		scan2.addColumn(Bytes.toBytes("emp"), Bytes.toBytes("first_name"));
		ResultScanner scanner2 = table.getScanner(scan2);
		for (Result result : scanner2) {
			for (Cell cell : result.rawCells()) {
				System.out.println("Cell: " + cell + ", Value: "
						+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
			}

		}scanner2.close();*/

		scanner.close();
		
	}
}
