package com.edge.edgeCollection.doublylinkedlist;

import java.util.LinkedList;

public class Main {

	public static void main (String [] args) {
		Employee janeJones =new Employee(1,"Jane", "Jones");
		Employee johnDoe =new Employee(2,"John", "Doe");
		Employee marySmith =new Employee(3,"Mary", "Smith");
		Employee mikeWilson =new Employee(4,"Mike", "Wilson");
		
		
		LinkedList list=new LinkedList();
		list.add(janeJones);
		list.add(johnDoe);
		list.add(marySmith);
		list.add(mikeWilson);
		System.out.println(list);	
		
		EmployeeLinkedList empList=new EmployeeLinkedList();
		empList.add(janeJones);
		empList.add(johnDoe);
		empList.add(marySmith);
		empList.add(mikeWilson);	
		empList.print();
 	}
	
	
}
