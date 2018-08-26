package com.edge.udf.udf;

/**
 * export as case.jar
 * hive> ADD JAR case.jar;
 * hive> CREATE TEMPORARY FUNCTION toLowerCase as 'com.edge.udf.ToLowerCase';
 * hive> select toLowerCase(first_name) from employees limit 2;
 */

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class ToLowerCase  extends UDF{
	public Text evaluate(Text str) {
		if (str == null)return null;
		return new Text(str.toString().toLowerCase());
	}
}
