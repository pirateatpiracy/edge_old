package com.edge.collection.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FindDups {
	String[] input = { "i", "came", "i", "saw", "i", "left" };

	public static void main(String[] args) {
		FindDups obj = new FindDups();
		obj.aggregate();
		obj.forEach();
		obj.modify();
	}

	void aggregate() {
		Set<String> distinctWords = Arrays.asList(input).stream().collect(Collectors.toSet());
		System.out.println(distinctWords.size() + " distinct words: " + distinctWords);
	}

	void forEach() {
		Set<String> s = new HashSet<String>();
		Set<String> t = new TreeSet<String>();		
		for (String a : input)s.add(a);
		for (String a : input)t.add(a);
		System.out.println(s.size() + " distinct words: " + s);
		System.out.println(t.size() + " distinct words: " + t);
	}
	
	void modify(){
		Set<String> set1 = new HashSet<String>();
		set1.add("ONE");     
		set1.add("TWO");     
		set1.add("THREE");   
		set1.add("FOUR");    
		System.out.println("\n"+set1);     //Output : [ONE, TWO, THREE, FOUR]
		Set<String> set2 = new HashSet<String>();
		set2.add("THREE");
		set2.add("FOUR");
		set2.add("FIVE");
		set2.add("SIX");
		System.out.println(set2);		//Output : [ONE, FOUR, TWO, THREE]
		set1.addAll(set2);				//transforms s1 into the union of s1 and s2. 
		System.out.println(set1);		//Output : [FIVE, SIX, ONE, FOUR, TWO, THREE]
		set1.removeAll(set2);			// transforms s1 into the (asymmetric) set difference of s1 and s2
		System.out.println(set1);		//Output : [ONE, TWO]
		set1.addAll( set2);				//transforms s1 into the union of s1 and s2. 
		System.out.println(set1);		//Output : [FIVE, SIX, ONE, FOUR, TWO, THREE]
		set1.retainAll(set2);			//transforms s1 into the intersection of s1 and s2. 
		System.out.println(set1);		//Output : [FIVE, SIX, FOUR, THREE]
		set1.clear();					//Removes all elements 
		System.out.println(set1);		//Output : []
	}

}
