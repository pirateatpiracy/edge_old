package com.edge;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Java7vs8 {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {

		// Using HashMap
		System.out.println("Using plain hash map with balanced trees:");
		HashMap stringMap = new HashMap();
		for (int i = 0; i < 10; ++i)
			stringMap.put("index_" + i, String.valueOf(i));
	//	stringMap.values().forEach(System.out::println);

		// Using LinkedHashMap
		System.out.println("Using LinkedHashMap:");
		LinkedHashMap linkedHashMap = new LinkedHashMap();
		for (int i = 0; i < 10; ++i)
			linkedHashMap.put("index_" + i, String.valueOf(i));
		//linkedHashMap.values().forEach(System.out::println);

	}

}


