package com.edge;

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

public class Scann {
	public static void main(String[] args) throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Configuration conf = HBaseConfiguration.create();
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf("emp"));
		

		System.out.println("Scanning table #1...");
		Scan scan1 = new Scan();
		ResultScanner scanner1 = table.getScanner(scan1);
		for (Result result : scanner1) {
			for (Cell cell : result.rawCells()) {
			System.out.println("Cell: " + cell + ", Value: "
					+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
		}}scanner1.close();
		
		

		System.out.println("\n\n\nScanning table #2...");
		Scan scan2 = new Scan();
		scan2.addFamily(Bytes.toBytes("name"));
		ResultScanner scanner2 = table.getScanner(scan2);
		for (Result result : scanner2) {
			for (Cell cell : result.rawCells()) {
			System.out.println("Cell: " + cell + ", Value: "
					+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
		}}scanner2.close();
		
		

		System.out.println("\n\n\nScanning table #3...");
		Scan scan3 = new Scan();
		scan3.addColumn(Bytes.toBytes("name"), Bytes.toBytes("name_first"))
				.addColumn(Bytes.toBytes("name"), Bytes.toBytes("name_last")).setStartRow(Bytes.toBytes("10007"))
				.setStopRow(Bytes.toBytes("10017"));
		ResultScanner scanner3 = table.getScanner(scan3);
		for (Result result : scanner3) {
			for (Cell cell : result.rawCells()) {
			System.out.println("Cell: " + cell + ", Value: "
					+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
		}}scanner3.close();
		
		

		System.out.println("\n\n\nScanning table #4...");
		Scan scan4 = new Scan();
		scan4.addColumn(Bytes.toBytes("name"), Bytes.toBytes("name_first")).setStartRow(Bytes.toBytes("10007"))
				.setStopRow(Bytes.toBytes("10017"));
		ResultScanner scanner4 = table.getScanner(scan4);
		for (Result result : scanner4) {
			for (Cell cell : result.rawCells()) {
			System.out.println("Cell: " + cell + ", Value: "
					+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
		}}scanner4.close();
		
		

		System.out.println("\n\n\nScanning table #5...");
		Scan scan5 = new Scan();
		scan5.addColumn(Bytes.toBytes("name"), Bytes.toBytes("name_first")).setStartRow(Bytes.toBytes("10017"))
				.setStopRow(Bytes.toBytes("10007")).setReversed(true);
		ResultScanner scanner5 = table.getScanner(scan5);
		for (Result result : scanner5) {
			for (Cell cell : result.rawCells()) {
			System.out.println("Cell: " + cell + ", Value: "
					+ Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
		}}scanner5.close();
		
		

		table.close();
		connection.close();
	}
}
