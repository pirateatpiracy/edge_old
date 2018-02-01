package com.edge.collection.set;

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

}
