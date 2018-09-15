package com.edge.collection.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.collections4.comparators.ComparatorChain;

public class Main {

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(10, "Mikey", 25, 10000));
		empList.add(new Employee(20, "Arun", 29, 20000));
		empList.add(new Employee(5, "Lisa", 35, 50000));
		empList.add(new Employee(1, "Pankaj", 25, 5000));

		System.out.println("Before sort ");
		for (Employee emp : empList)System.out.println(emp);
		
		System.out.println("\nAfter sort with comparable");
		Collections.sort(empList);
		for (Employee emp : empList)System.out.println(emp);
		
		System.out.println("\nAfter sort with Idcomparator");
		Collections.sort(empList, new IdCompare());		
		for (Employee emp : empList)System.out.println(emp);

		System.out.println("\nAfter sort with NameComparator");
		Collections.sort(empList, new NameCompare());
		for (Employee emp : empList)System.out.println(emp);

		System.out.println("\nAfter sort with SalaryComparator");
		Collections.sort(empList, new Comparator<Employee>() { // anonymous inner class
			public int compare(Employee emp1, Employee emp2) {
				return (emp1.getSalary() - emp2.getSalary());
			}
		});
		for (Employee emp : empList)System.out.println(emp);

		System.out.println("\nAfter sort with AgeComparator");
		Comparator<Employee> AgeCompare = (emp1, emp2) -> emp1.getAge() - emp2.getAge(); // Java 8 Lambda 
		Collections.sort(empList, AgeCompare);
		for (Employee emp : empList)System.out.println(emp);
		
		System.out.println("\nAfter sort with ComparatorChain first age, then name");
	     ComparatorChain<Employee> chain = new ComparatorChain<Employee>(); // ComparatorChain
         chain.addComparator(AgeCompare);  
         chain.addComparator(new NameCompare()); 
         Collections.sort(empList, chain);
         for (Employee emp : empList)System.out.println(emp);
         
         
         System.out.println("\nAfter sort with ComparatorChain java 8 first age, then salary");	    
         Collections.sort(empList, new ChainingComparator());
         for (Employee emp : empList)System.out.println(emp);

	}

}

class IdCompare implements Comparator<Employee> {
	public int compare(Employee emp1, Employee emp2)// using own logic
	{
		if (emp1.getId() < emp1.getId())return -1;
		if (emp1.getId() > emp1.getId())return 1;
		else return 0;
	}
}

class NameCompare implements Comparator<Employee>// using compareTo
{
	public int compare(Employee emp1, Employee emp2) {
		return emp1.getName().compareTo(emp2.getName());
	}
}

class ChainingComparator implements Comparator<Employee> { // Comparator chaining java 8
	public int compare(Employee emp1, Employee emp2) {
		Comparator<Employee> c;
		c = Comparator.comparing(emp -> emp.getAge());
		c = c.thenComparingInt(emp -> emp.getSalary());
		return c.compare(emp1, emp2);
	}
}
