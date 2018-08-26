package definitiveGuide.chapter04.filters;

// cc InclusiveStopFilterExample Example using a filter to include a stop row
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
import org.apache.hadoop.hbase.filter.InclusiveStopFilter;
import org.apache.hadoop.hbase.util.Bytes;

import definitiveGuide.util.HBaseHelper;

public class InclusiveStopFilterExample {

  public static void main(String[] args) throws IOException {
    Configuration conf = HBaseConfiguration.create();

    HBaseHelper helper = HBaseHelper.getHelper(conf);
    helper.dropTable("testtable");
    helper.createTable("testtable", "colfam1");
    System.out.println("Adding rows to table...");
    helper.fillTable("testtable", 1, 100, 1, "colfam1");

    Connection connection = ConnectionFactory.createConnection(conf);
    Table table = connection.getTable(TableName.valueOf("testtable"));
    // vv InclusiveStopFilterExample
    Filter filter = new InclusiveStopFilter(Bytes.toBytes("row-5"));

    Scan scan = new Scan();
    scan.setStartRow(Bytes.toBytes("row-3"));
    scan.setFilter(filter);
    ResultScanner scanner = table.getScanner(scan);
    // ^^ InclusiveStopFilterExample
    System.out.println("Results of scan:");
    // vv InclusiveStopFilterExample
    for (Result result : scanner) {
      System.out.println(result);
    }
    scanner.close();
    // ^^ InclusiveStopFilterExample
  }
}
