package tutorial;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.log4j.PropertyConfigurator;
import org.apache.hadoop.hbase.TableName;

import org.apache.hadoop.conf.Configuration;

public class CreateTable {

	public static void main(String[] args) throws IOException {
		System.setProperty("hadoop.home.dir", "C:\\Users\\edge\\Downloads\\Programs");
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath); 
		Configuration hBaseConfig = HBaseConfiguration.create();
	   // hBaseConfig.setInt("timeout", 120000);
	    hBaseConfig.set("hbase.master", "192.168.221.140" + ":9000");
	    hBaseConfig.set("hbase.zookeeper.quorum","192.168.221.140");
	    hBaseConfig.set("hbase.zookeeper.property.clientPort", "2181");
		HBaseAdmin admin = new HBaseAdmin(hBaseConfig);
		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("testemp"));
		tableDescriptor.addFamily(new HColumnDescriptor("personal"));
		tableDescriptor.addFamily(new HColumnDescriptor("professional"));
		admin.createTable(tableDescriptor); 
		System.out.println(" Table created ");
		admin.close();
	}
}