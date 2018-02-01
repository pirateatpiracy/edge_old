package com.edge.collection.list;

import java.util.ArrayList;
import java.util.ListIterator;

class ArrayListDemo {
	public static void main(String[] args) {
		ArrayListDemo obj = new ArrayListDemo();
		obj.basic();
		obj.speedTest();
		obj.traverse();
		obj.modify();
	}

	void basic() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("ZERO");
		list.add("TWO");
		list.add("FOUR");
		System.out.println(list); // Output : [ZERO, TWO, FOUR]
		list.add(2, "THREE"); // Inserting an element at index 2
		System.out.println(list);// [ZERO, TWO, THREE, FOUR]
		list.add(1, "ONE"); // Inserting an element at index 1
		System.out.println(list); // Output : [ZERO, ONE, TWO, THREE, FOUR]
		list.remove(3); // Removing an element from index 3
		System.out.println(list); // Output : [ZERO, ONE, TWO, FOUR]}
		try {list.add(20, "THREE");}  // Throws exception
		catch (Exception e) {e.printStackTrace();}
	}
	
	/*
	 * Many are of the assumption that multiple insertion and removal operations on ArrayList will decrease the 
	 * performance of an application. But, there will be no significant change in the performance of an application 
	 * if you use ArrayList instead of arrays. Below example shows time taken to add 1000 string elements to ArrayList and array.
	*/
	void speedTest() {String[] namesArray = new String[1000000];

	long startTime = System.currentTimeMillis();
	for (int i = 0; i < namesArray.length; i++)	namesArray[i] = "Name"+i;
	long endTime = System.currentTimeMillis();          
	System.out.println("\n\nTime taken by Array : "+(endTime - startTime)+"ms");
	
	ArrayList<String> nameList = new ArrayList<String>();     
	startTime = System.currentTimeMillis();
	for (int i = 0; i <= 1000000; i++)nameList.add("Name"+i);
	endTime = System.currentTimeMillis();
	System.out.println("Time taken by ArrayList : "+(endTime-startTime)+"ms");
	}
//can traverse an ArrayList in both the directions – forward and backward using ListIterator.	
	void traverse(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("ONE");
		list.add("TWO");
		list.add("THREE");
		list.add("FOUR");
		ListIterator iterator = list.listIterator();
		System.out.println("\n\nElements in forward direction");
		while (iterator.hasPrevious())System.out.print(iterator.previous()+" ");//doesn't print anything , because the pointer is at the begining.
		while (iterator.hasNext())System.out.print(iterator.next()+" ");//ONE TWO THREE FOUR 
		System.out.println("\nElements in backward direction");
		while (iterator.hasPrevious())System.out.print(iterator.previous()+" ");//FOUR THREE TWO ONE

	}
	
	void modify(){
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("ONE");     
		list1.add("TWO");     
		list1.add("THREE");   
		list1.add("FOUR");    
		System.out.println("\n"+list1);     //Output : [ONE, TWO, THREE, FOUR]
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("THREE");
		list2.add("FOUR");
		list2.add("FIVE");
		list2.add("SIX");
		System.out.println(list2);     //Output : [THREE, FOUR, FIVE, SIX]
		list1.addAll(list2);   //Appends list2 at the end of list1
		System.out.println(list1);    //Output : [ONE, TWO, THREE, FOUR, THREE, FOUR, FIVE, SIX]
		list1.removeAll(list2);    //Removes the elements of list1 which are also elements of list2
		System.out.println(list1);    //Output : [ONE, TWO]
		list1.addAll(2, list2);    //Inserts all elements of list2 into list1 at position 2
		System.out.println(list1);    //Output : [ONE, TWO, THREE, FOUR, FIVE, SIX]
		list1.retainAll(list2);    //Retains all elements of list1 which are also elements of list2
		System.out.println(list1);    //Output : [THREE, FOUR, FIVE, SIX]
		list1.clear();      //Removes all elements of list1
		System.out.println(list1);   //Output : []
	}
}