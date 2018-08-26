package com.edge.collection.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Java8 {
	public static void main (String [] a) {
		Java8 obj=new Java8();
		obj.ifAbsent();
		obj.merge();
	}

	void ifAbsent() {
		Map<String, String> favorites = new HashMap<>();
		favorites.put("Jenny", "Bus Tour");
		favorites.put("Jenny", "Tram");
		System.out.println(favorites); // {Jenny=Tram}
		favorites.put("Jenny", "Bus Tour");
		favorites.put("Tom", null);
		favorites.putIfAbsent("Jenny", "Tram");
		favorites.putIfAbsent("Sam", "Tram");
		favorites.putIfAbsent("Tom", "Tram");
		System.out.println(favorites); // {Tom=Tram, Jenny=Bus Tour, Sam=Tram}
		}
	
	void merge() {
		BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1: v2;
		Map<String, String> favorites = new HashMap<>();
		favorites.put("Jenny", "Bus Tour");
		favorites.put("Tom", "Tram");
		
		String jenny = favorites.merge("Jenny", "Skyride", mapper);
		String tom = favorites.merge("Tom", "Skyride", mapper);
		
		System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}
		System.out.println(jenny); // Bus Tour
		System.out.println(tom); // Skyride
	}
}
