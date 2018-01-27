package com.edge.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Reflection {

	public static void main(String args[])
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Reflection reflection = new Reflection();
		reflection.getClassName1();
		reflection.getClassName2();
		reflection.getClassName3();
		reflection.checkType();
		reflection.newInstance();
		reflection.invokeConstructor();
		reflection.getMethods();
		reflection.executeMethods();

	}

	void getClassName1() throws ClassNotFoundException {
		System.out.println(":3 wyas of getClassName");
		Class c = Class.forName("com.edge.reflection.ReflectionTest");
		System.out.println(c.getName());
	}

	void getClassName2() {
		ReflectionTest reflectionTest = new ReflectionTest();
		Class c = reflectionTest.getClass();
		System.out.println(c.getName());
	}

	void getClassName3() {
		Class c = ReflectionTest.class;
		System.out.println(c.getName());
	}

	void checkType() {
		System.out.println("\n:checkType");
		Class c = ReflectionTest.class;
		System.out.println("isInterface():	"  + c.isInterface());
		System.out.println("isArray():	" + c.isArray());
		System.out.println("isPrimitive():	" + c.isPrimitive());
		c = ReflectionTestInterface.class;
		System.out.println("isInterface():	" + c.isInterface());
	}

	void newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println("\n:newInstance");
		Class c = Class.forName("com.edge.reflection.ReflectionTest");
		ReflectionTest reflectionTest = (ReflectionTest) c.newInstance();
		reflectionTest.method4();
	}

	void invokeConstructor() throws NoSuchMethodException, SecurityException {
		System.out.println("\n:invokeConstructor");
		ReflectionTest reflectionTest = new ReflectionTest();
		Class c = reflectionTest.getClass();
		Constructor constructor = c.getConstructor();
		System.out.println(constructor.getName());
	}

	void getMethods() {
		System.out.println("\n:getMethods");
		ReflectionTest reflectionTest = new ReflectionTest();
		Class c = reflectionTest.getClass();
		System.out.println("The public methods of class are : ");
		Method[] methods = c.getMethods();
		for (Method method : methods)
			System.out.println(method.getName());
	}

	void executeMethods() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		System.out.println("\n:executeMethods");
		ReflectionTest reflectionTest = new ReflectionTest();
		Class c = reflectionTest.getClass();

		Method methodcall1 = c.getDeclaredMethod("method1", int.class);
		methodcall1.invoke(reflectionTest, 19);

		Field field = c.getDeclaredField("s");
		field.setAccessible(true);
		field.set(reflectionTest, "JAVA");
		Method methodcall2 = c.getDeclaredMethod("method2");
		methodcall2.invoke(reflectionTest);

		Method methodcall3 = c.getDeclaredMethod("method3");
		methodcall3.setAccessible(true);
		methodcall3.invoke(reflectionTest);
	}
}
