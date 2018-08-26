package com.edge.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {

	public static void main(String[] a) {
		new Search().search();
	}

	void search() {
		int[] numbers1 = { 2, 4, 6, 8 };
		System.out.println(Arrays.binarySearch(numbers1, 2)); // 0
		System.out.println(Arrays.binarySearch(numbers1, 4)); // 1
		System.out.println(Arrays.binarySearch(numbers1, 1)); // -1
		System.out.println(Arrays.binarySearch(numbers1, 3)); // -2
		System.out.println(Arrays.binarySearch(numbers1, 9)); // -5

		int[] numbers2 = new int[] { 3, 2, 1 };
		System.out.println(Arrays.binarySearch(numbers2, 2));
		System.out.println(Arrays.binarySearch(numbers2, 3));
		
		
		List<String> list = new ArrayList<String>();
		list.add("hawk");
		list.add("robin");
		Object[] objectArray = list.toArray();
		System.out.println(objectArray.length); // 2
		String[] stringArray = list.toArray(new String[0]);
		System.out.println(stringArray.length); // 2
	}
}
