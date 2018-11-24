package com.edge.customValueSort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class Employee implements Writable, WritableComparable<Employee> {

	private String deptNo;
	private String titleName;
	
	public Employee() {super();}
	public Employee(String deptNo, String titleName) {
		this.deptNo = deptNo;
		this.titleName = titleName;
	}

	
	public String getDeptNo() {return deptNo;}
	public void setDeptNo(String deptNo) {this.deptNo = deptNo;}
	public String getTitleName() {return titleName;}
	public void setTitleName(String titleName) {this.titleName = titleName;}
	

	
	public void readFields(DataInput dataInput) throws IOException {
		deptNo = WritableUtils.readString(dataInput);
		titleName = WritableUtils.readString(dataInput);
	}

	
	public void write(DataOutput dataOutput) throws IOException {
		WritableUtils.writeString(dataOutput, deptNo);
		WritableUtils.writeString(dataOutput, titleName);
	}

	
	public int compareTo(Employee emp) {
		int result = deptNo.compareTo(emp.deptNo);
		if (0 == result)
			result = titleName.compareTo(emp.titleName);		
		return result;
	}
	
		
	public String toString() {
		return (new StringBuilder().append(deptNo).append("\t").append(titleName)).toString();
	}

}
