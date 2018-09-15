package com.edge.passBy;

class Employee {
	String name;
	int age;
	float sal;
	String address;
	public Employee() {
		super();
	}
	public Employee(String name, int age, float sal, String address) {
		super();
		this.name = name;
		this.age = age;
		this.sal = sal;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", sal=" + sal + ", address=" + address + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
		
}