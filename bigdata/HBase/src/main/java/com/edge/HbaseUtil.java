package com.edge;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class HbaseUtil {

	private Configuration configuration = null;
	private Connection connection = null;
	private Admin admin = null;

	public HbaseUtil(Configuration configuration) throws IOException {
		this.configuration = configuration;
		this.connection = ConnectionFactory.createConnection(configuration);
		this.admin = connection.getAdmin();
	}

	public void dropTable(String table) throws IOException {

		if (existsTable(table)) {
			if (admin.isTableEnabled(TableName.valueOf(table)))
				admin.disableTable(TableName.valueOf(table));
			admin.deleteTable(TableName.valueOf(table));
		}
	}

	public boolean existsTable(String table) throws IOException {
		return admin.tableExists(TableName.valueOf(table));
	}

}
