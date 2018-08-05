package com.edge.passBy;

public class Developer extends Employee{
	
	String []skills;
	Project project;
	public Developer() {
		super();
	}
	public Developer(String name, int age, float sal, String address) {
		super(name, age, sal, address);
	}
	public Developer(String name, int age, float sal, String address, String[] skills, Project project) {
		super(name, age, sal, address);
		this.skills = skills;
		this.project = project;
	}
}


