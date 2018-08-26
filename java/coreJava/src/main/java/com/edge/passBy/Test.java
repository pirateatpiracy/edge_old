package com.edge.passBy;

class Test{
	public static void main (String [] a) {
		
		Employee emp=new Employee("Edge", 30, 30, "Bangalore");
		Developer developer =(Developer) emp;
		System.out.println(developer);
	}
}