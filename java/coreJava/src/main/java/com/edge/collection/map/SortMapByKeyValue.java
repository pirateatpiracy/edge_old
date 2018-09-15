package com.edge.collection.map;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortMapByKeyValue {
	Map<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String[] args) {

		SortMapByKeyValue smkv = new SortMapByKeyValue();
		smkv.createMap();

		System.out.println("After sorting by key ascending order......");
		smkv.sortByKey(true);

		System.out.println("After sorting by key descindeng order......");
		smkv.sortByKey(false);

		System.out.println("After sorting by value ascending order......");
		smkv.sortByValue(true);

		System.out.println("After sorting by value  descindeng order......");
		smkv.sortByValue(false);

	}

	void createMap() {
		map.put("B", 55);
		map.put("A", 80);
		map.put("D", 20);
		map.put("C", 70);
		map.put("AC", 70);
		map.put("BC", 70);
		System.out.println("Before sorting......");
		printMap(map);
	}

	void sortByValue(boolean order) {

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		printMap(sortedMap);

	}

	void sortByKey(boolean order) {

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getKey().compareTo(o2.getKey());
				} else {
					return o2.getKey().compareTo(o1.getKey());

				}
			}
		});
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		printMap(sortedMap);
	}

	public void printMap(Map<String, Integer> map) {
		// System.out.println(map);
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}