package com.geekcap.informit.hbaseexample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.PropertyConfigurator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBaseExample {
	private HTableInterface pageViewTable;
	Configuration hBaseConfig = HBaseConfiguration.create();
	public HBaseExample() {
		try {
		//	System.setProperty("hadoop.home.dir", "C:\\Users\\edge\\Downloads\\Programs");
			hBaseConfig = HBaseConfiguration.create();
		//	hBaseConfig.setInt("timeout", 120000);
	/*		hBaseConfig.set("hbase.master", "192.168.221.140" + ":9000");
			hBaseConfig.set("hbase.zookeeper.quorum","192.168.221.140");
			hBaseConfig.set("hbase.zookeeper.property.clientPort", "2181");*/
			pageViewTable = new HTable(hBaseConfig, "PageViews");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void create() throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		HBaseAdmin admin = new HBaseAdmin(hBaseConfig);
		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("PageViews"));
		tableDescriptor.addFamily(new HColumnDescriptor("info"));
		//tableDescriptor.addFamily(new HColumnDescriptor("professional"));
		admin.createTable(tableDescriptor); 
		System.out.println(" Table created ");
		admin.close();
	}
	public void close() {
		try {
			pageViewTable.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void put(PageView pageView) {
		Put put = new Put(Bytes.toBytes(pageView.getUserId()));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("userId"), Bytes.toBytes(pageView.getUserId()));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("page"), Bytes.toBytes(pageView.getPage()));
		try {
			pageViewTable.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public PageView get(String rowkey) {
		try {
			Get get = new Get(Bytes.toBytes(rowkey));
			Result result = pageViewTable.get(get);
			PageView pageView = new PageView();
			byte[] bytes = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("userId"));
			pageView.setUserId(Bytes.toString(bytes));
			bytes = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("page"));
			pageView.setPage(Bytes.toString(bytes));
			return pageView;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void delete(String rowkey) {
		try {
			Delete delete = new Delete(Bytes.toBytes(rowkey));
			pageViewTable.delete(delete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<PageView> scan(String startRowKey, String endRowKey) {
		try {
			List<PageView> pageViewResults = new ArrayList<PageView>();
			Scan scan = new Scan(Bytes.toBytes(startRowKey), Bytes.toBytes(endRowKey));
			ResultScanner results = pageViewTable.getScanner(scan);
			for (Result result : results) {
				PageView pageView = new PageView();
				byte[] bytes = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("userId"));
				pageView.setUserId(Bytes.toString(bytes));
				bytes = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("page"));
				pageView.setPage(Bytes.toString(bytes));
				pageViewResults.add(pageView);
			}
			return pageViewResults;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		HBaseExample example = new HBaseExample();
		example.create();
		example.put(new PageView("User1", "/mypage"));
		example.put(new PageView("User2", "/mypage"));
		example.put(new PageView("User3", "/mypage"));
		example.put(new PageView("User4", "/mypage"));
		example.put(new PageView("User5", "/mypage"));
		example.put(new PageView("User6", "/mypage"));
		example.put(new PageView("User7", "/mypage"));
		example.put(new PageView("User8", "/mypage"));
		example.put(new PageView("User9", "/mypage"));
		example.put(new PageView("User10", "/mypage"));
		List<PageView> pageViews = example.scan("U", "V");
		if (pageViews != null) {
			System.out.println("Page Views:");
			for (PageView pageView : pageViews) {
				System.out.println("\tUser ID: " + pageView.getUserId() + ", Page: " + pageView.getPage());
			}
		}
		PageView pv = example.get("User1");
		System.out.println("User ID: " + pv.getUserId() + ", Page: " + pv.getPage());
		example.delete("User1");
		pageViews = example.scan("U", "V");
		if (pageViews != null) {
			System.out.println("Page Views:");
			for (PageView pageView : pageViews) {
				System.out.println("\tUser ID: " + pageView.getUserId() + ", Page: " + pageView.getPage());
			}
		}
		example.close();
	}
}