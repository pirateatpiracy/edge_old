package com.edge.filter;

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
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.InclusiveStopFilter;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.Cell;
import org.apache.log4j.PropertyConfigurator;

public class AE_MultipleColumnPrefix {
	public static void main(String[] args) throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Configuration conf = HBaseConfiguration.create();
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf(Bytes.toBytes("emp_hive")));
		Scan scan = new Scan();
		
		byte[][] prefixes = new byte [2] [];
		prefixes[0]=Bytes.toBytes("first");
		prefixes[1]=Bytes.toBytes("last");
		Filter prefixFilters = new MultipleColumnPrefixFilter(prefixes);
		
		Filter prefixFilter = new PrefixFilter(Bytes.toBytes("10017"));
		Filter inclusiveStopFilter= new InclusiveStopFilter(Bytes.toBytes("10017"));
		
		FilterList filterList=new FilterList();
		filterList.addFilter(prefixFilters);
		filterList.addFilter(inclusiveStopFilter);
		//filterList.addFilter(prefixFilter);
		scan.setFilter(filterList);
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
		scanner.close();
	}
}
