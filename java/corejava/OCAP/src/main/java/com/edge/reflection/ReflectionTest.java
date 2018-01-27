package com.edge.reflection;

interface ReflectionTestInterface {
}

public class ReflectionTest {
	private String s;

	public ReflectionTest() {
		s = "Edge";
	}

	public void method1(int n) {
		System.out.println("The number is " + n);
	}

	public void method2() {
		System.out.println("The string is " + s);
	}

	private void method3() {
		System.out.println("Private method invoked");
	}

	void method4() {
		System.out.println("Just checking");
	}
}