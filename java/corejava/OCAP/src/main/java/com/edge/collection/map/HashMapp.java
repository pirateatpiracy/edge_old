package com.edge.collection.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapp {

	HashMap<String, String> edge = new HashMap<>();

	public static void main(String[] args) {
		HashMapp obj = new HashMapp();
		obj.putt();
		obj.isEmptyy();
		obj.keySett();
		obj.entrySett();
		obj.getOrDefaultt();
		obj.replace();
		obj.putIfAbsent();
		obj.remove();
		
		
	}	

	void putt() {
		System.out.println(edge.put("Language", "Java"));// null
		System.out.println(edge.put("Platform", "edge For edge"));// null
		System.out.println(edge.put("Code", "HashMap"));// null
		System.out.println(edge.put("Learn", "More"));// null
		System.out.println(edge.put("Language", "ADVJava"));//Java
	}

	void isEmptyy() {
		System.out.println("\n\nTesting .isEmpty() method");
		if (!edge.isEmpty()) {
			System.out.println("HashMap edge is notempty");
			System.out.println("edge : " + edge.get("Language"));
			
			System.out.println("Size Of HashMap : " + edge.size());// size() method prints the size of HashMap.
		}
	}
	void keySett() {
		Set<String> edgekeys = edge.keySet();//keySet(): java.util.HashMap.keySet() It returns a Set view of the keys contained in this map. The set is backed by the map.
        System.out.println("\n\nInitial keys  : " + edgekeys);//Initial keys  : [Language, Platform, Learn, Code]
        
        Collection<String> edgevalues = edge.values();//values(): java.util.HashMap.values() It returns a Collection view of the values contained in this map. The collection is backed by the map
        System.out.println("Initial values : " + edgevalues);//Initial values : [ADVJava, edge For edge, More, HashMap]
        
        System.out.println("\n\n"+edge.put("BigData", "Hadoop"));//null
        System.out.println(edge.put("Language", "Hadoop"));//ADVJava
		System.out.println(edge.put("Platform", "Mapreduce"));//edge For edge
		System.out.println(edge.put("Code", "Hive"));//HashMap
		System.out.println(edge.put("Learn", "Pig"));//More
		
		System.out.println("\n\nInitial keys  : " + edgekeys);//Changes to the map are reflected in the set, and vice-versa.
		System.out.println("Initial values : " + edgevalues);//Changes to the map are reflected in the collection, and vice-versa.
		
	}
	
	void entrySett() {
		 Set<Map.Entry<String, String>> mappingGeeks = edge.entrySet();
	     System.out.println("\n\nSet of Keys and Values using entrySet() : "+mappingGeeks);// [Language=Hadoop, Platform=Mapreduce, BigData=Hadoop, Learn=Pig, Code=Hive]
	     System.out.println();
	}
	
	void getOrDefaultt() {}
	void replace() {}
	void putIfAbsent () {}
	void remove(){}
}
