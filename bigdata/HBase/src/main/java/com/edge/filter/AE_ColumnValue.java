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
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.PropertyConfigurator;

public class AE_ColumnValue {
	
	public static void main(String[] args) throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Configuration conf = HBaseConfiguration.create();
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf("emp_hive"));
		Scan scan = new Scan();
		FilterList filterList = new FilterList();
		Filter first_name =  new SingleColumnValueFilter(Bytes.toBytes("d"),Bytes.toBytes("first_name"),CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("Kazuhito")));
		Filter last_name =  new SingleColumnValueFilter(Bytes.toBytes("d"),Bytes.toBytes("last_name"),CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("Peha")));
		filterList.addFilter(first_name);
		filterList.addFilter(last_name);
		ResultScanner scanner;
		int rowCount = 0;
		
		
		scan.setFilter(first_name);
		scanner = table.getScanner(scan);
		rowCount = 0;
		for (Result result : scanner) {
			for (Cell cell : result.rawCells()) {
				System.out.println("Cell: " + cell + ", Value: "
						+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
			}
			rowCount++;
		}System.out.println("Total row found: " + rowCount+"\n\n\n");
		
		
		scan.setFilter(last_name);	
		scanner = table.getScanner(scan);
		rowCount = 0;
		for (Result result : scanner) {
			for (Cell cell : result.rawCells()) {
				System.out.println("Cell: " + cell + ", Value: "
						+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
			}
			rowCount++;
		}System.out.println("Total row found: " + rowCount+"\n\n\n");
		
		scan.setFilter(filterList);
		scanner = table.getScanner(scan);
		rowCount = 0;
		for (Result result : scanner) {
			for (Cell cell : result.rawCells()) {
				System.out.println("Cell: " + cell + ", Value: "
						+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
			}
			rowCount++;
		}System.out.println("Total row found: " + rowCount);
		scanner.close();
	}
}
