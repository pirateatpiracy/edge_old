package com.edge.collection.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapp {

	HashMap<String, String> edge = new HashMap<>();
	 Set<Map.Entry<String, String>> mappingedge ;
	public static void main(String[] args) {
		HashMapp obj = new HashMapp();
		obj.putt();
		obj.isEmptyy();
		obj.keySett();
		obj.entrySett();
		obj.getOrDefaultt();
		obj.replacee();
		obj.putIfAbsentt();
		obj.removee();
		
		
	}	

	
	void putt() {System.out.println("\n\n**** putt:");
		System.out.println(edge.put("Language", "Java"));// null
		System.out.println(edge.put("Platform", "edge For edge"));// null
		System.out.println(edge.put("Code", "HashMap"));// null
		System.out.println(edge.put("Learn", "More"));// null
		System.out.println(edge.put("Language", "ADVJava"));//Java
	}

	void isEmptyy() {System.out.println("\n\n**** isEmptyy:");
		if (!edge.isEmpty()) {
			System.out.println("HashMap edge is notempty");
			System.out.println("edge : " + edge.get("Language"));
			
			System.out.println("Size Of HashMap : " + edge.size());// size() method prints the size of HashMap.
		}
	}
	void keySett() {System.out.println("\n\n**** keySett:");
		Set<String> edgekeys = edge.keySet();//keySet(): java.util.HashMap.keySet() It returns a Set view of the keys contained in this map. The set is backed by the map.
        System.out.println("Initial keys  : " + edgekeys);//Initial keys  : [Language, Platform, Learn, Code]
        
        Collection<String> edgevalues = edge.values();//values(): java.util.HashMap.values() It returns a Collection view of the values contained in this map. The collection is backed by the map
        System.out.println("Initial values : " + edgevalues);//Initial values : [ADVJava, edge For edge, More, HashMap]
        
        System.out.println("\n"+edge.put("BigData", "Hadoop"));//null
        System.out.println(edge.put("Language", "Hadoop"));//ADVJava
		System.out.println(edge.put("Platform", "Mapreduce"));//edge For edge
		System.out.println(edge.put("Code", "Hive"));//HashMap
		System.out.println(edge.put("Learn", "Pig"));//More
		
		System.out.println("\nInitial keys  : " + edgekeys);//Changes to the map are reflected in the set, and vice-versa.
		System.out.println("Initial values : " + edgevalues);//Changes to the map are reflected in the collection, and vice-versa.
		
	}
	
	
	//.entrySet() : java.util.HashMap.entrySet() method returns a complete set of keys and values present in the HashMap.
	void entrySett() { System.out.println("\n\n**** enentrySett:");
		mappingedge = edge.entrySet();		
	     System.out.println("Set of Keys and Values using entrySet() : "+mappingedge);// [Language=Hadoop, Platform=Mapreduce, BigData=Hadoop, Learn=Pig, Code=Hive]
	         
	     for (Map.Entry<String, String> entry : mappingedge) {
	    	 System.out.println(entry.getKey()+" : "+entry.getValue());
	     }
	}
	
	/*.getOrDefault : java.util.HashMap.getOrDefault() method returns the passed value if there is no value find using the key we passed as an argument in HashMap.
	 *  If the value for key if present already in the HashMap, it won�t do anything to it.*/
	void getOrDefaultt() {System.out.println("\n\n**** getOrDefaultt:");
		 System.out.println(edge.getOrDefault("Code","javaArticle"));
		 System.out.println(edge.getOrDefault("Default","javaArticle"));
		 System.out.println(edge.getOrDefault("Code","javaArticle"));
	}
	
	/*.replace() : java.util.HashMap.replace(key, value) or java.util.HashMap.replace(key, oldvalue, newvalue) method is a java.util.HashMap class method.
	 * 1st method accepts set of key and value which will replace the already present value of the key with the the new value passed in the argument. If no such set is present replace() method will do nothing.
	 * Meanwhile 2nd method will only replace the already present set of key-old_value if the key and old_Value are found in the HashMap.*/	 
	void replacee() { System.out.println("\n\n**** replacee:");
		edge.replace("Teach", "Methods");//No change, as 'Teach' was not present.
		System.out.println(mappingedge);//[Language=Hadoop, Platform=Mapreduce, BigData=Hadoop, Learn=Pig, Code=Hive]

		edge.replace("Learn", "Methods");
        System.out.println(mappingedge);//[Language=Hadoop, Platform=Mapreduce, BigData=Hadoop, Learn=Methods, Code=Hive]
       
        edge.replace("Learn", "Java" ,"C++");//No change as value was not ["Learn", "Java" ] but ["Learn", "Methods" ]
        System.out.println(mappingedge);//[Language=Hadoop, Platform=Mapreduce, BigData=Hadoop, Learn=Methods, Code=Hive]
        
        edge.replace("Learn", "Methods" ,"Java");
        System.out.println(mappingedge);//[Language=Hadoop, Platform=Mapreduce, BigData=Hadoop, Learn=Java, Code=Hive]
        
	}
	
/*.putIfAbsent java.util.HashMap.putIfAbsent(key, value) method is being used to insert a new key-value set to the HashMap 
 * if the respective set is present. Null value is returned if such key-value set is already present in the HashMap.*/
	void putIfAbsentt () { System.out.println("\n\n**** putIfAbsentt:");
		 edge.putIfAbsent("cool", "HashMap methods");
	        System.out.println(mappingedge);//[Language=Hadoop, Platform=Mapreduce, cool=HashMap methods, BigData=Hadoop, Learn=Methods, Code=Hive]

	}
	
	//remove(Object key): Removes the mapping for this key from this map if present.
	void removee(){ System.out.println("\n\n**** removee:");
		edge.putIfAbsent("Code", "With_JAVA");
        System.out.println(mappingedge);//[Language=Hadoop, Platform=Mapreduce, cool=HashMap methods, BigData=Hadoop, Learn=Methods, Code=Hive]

	}
}
