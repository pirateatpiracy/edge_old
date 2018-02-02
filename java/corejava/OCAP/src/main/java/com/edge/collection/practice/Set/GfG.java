package com.edge.collection.practice.Set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function would be added by GfG's Online Judge.*/


/* You are required to complete below methods */
class GfG
{
    /*inserts an element x to the set s */
    void insert(Set<Integer> s, int x)
    {
	s.add(x);
    }
	
    /*prints the contents of the set s in ascending order */
    void print_content(Set<Integer> s)
    {
    	List<Integer> sortedList = new ArrayList<Integer>(s);
    	Collections.sort(sortedList);
    	for (Integer a:s)System.out.print(a+" ");
    }
	
    void print_contents(Set<Integer> s)
    {
	// Your code here
	Set<Integer> p = new TreeSet<Integer>(s);
	for(Integer i:p){
	    System.out.print(i+" ");
	}
    }
    
    /*erases an element x from the set s */	
    void erase(Set<Integer> s, int x)
    {
	s.remove(x);
    }
	
    /*returns the size of the set s */
    int size(Set<Integer> s)
    {
       return s.size();
    }
		
    /*returns 1 if the element x is
    present in set s else returns -1 */
    int find(Set<Integer> s, int x)
    {
    	if(s.contains(x))return 1;
    		return -1;
    }
}