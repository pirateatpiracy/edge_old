/**
 * Without implementing this class the sorting will be of natural order(between two Employee objects).
 * Using this class, it will be sorted with deptNo, objects with same deptNo will be sorted with titleName.
 */
package com.edge.customValueSort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class KeyComparator extends WritableComparator {
	int flag;
	
	KeyComparator() {super(Employee.class, true);}

	@SuppressWarnings({ "rawtypes" })
	public int compare(WritableComparable w1, WritableComparable w2) {
		Employee emp1 = (Employee) w1;
		Employee emp2 = (Employee) w2;
		flag = emp1.getDeptNo().compareTo(emp2.getDeptNo());
		if (flag == 0)
			return emp1.getTitleName().compareTo(emp2.getTitleName());
		return flag;
	}
}
