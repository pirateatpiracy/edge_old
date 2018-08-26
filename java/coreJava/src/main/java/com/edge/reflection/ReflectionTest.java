package com.edge.reflection;

interface ReflectionTestInterface {
}

public class ReflectionTest {
	private String s;
	public String w;
	public String just;
	public ReflectionTest() {
		s = "Edge";
	}

	public void method1(int n) {
		System.out.println("The number is " + n);
	}

	public void method2(int n, String x) {
		System.out.println("The string is " + s+x+n);
	}

	private void method3() {
		System.out.println("Private method invoked");
	}

	void method4() {
		System.out.println("Just checking");
	}
}