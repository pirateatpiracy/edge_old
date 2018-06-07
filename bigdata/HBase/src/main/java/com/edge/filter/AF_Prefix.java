package com.edge.filter;

/*@author: Koushik
 * PrefixFilter : This filter takes one argument  – a prefix of a row key. 
 * It returns only those key-values present in a row that starts with the specified row prefix.*/

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
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.PropertyConfigurator;

public class AF_Prefix {
	public static void main(String[] args) throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Configuration conf = HBaseConfiguration.create();
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf("emp_hive"));
		Scan scan = new Scan();

		Filter prefixFilter = new PrefixFilter(Bytes.toBytes("100"));
		scan.setFilter(prefixFilter);
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
