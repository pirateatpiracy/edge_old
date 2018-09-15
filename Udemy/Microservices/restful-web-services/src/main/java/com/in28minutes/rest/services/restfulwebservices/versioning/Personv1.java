package com.in28minutes.rest.services.restfulwebservices.versioning;

public class Personv1 {

	public Personv1() {
		super();
	}

	public Personv1(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
