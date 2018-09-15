package com.in28minutes.rest.services.restfulwebservices.versioning;

public class Personv2 {

	public Personv2() {
		super();
	}


	public Name getName() {
		return name;
	}


	public void setName(Name name) {
		this.name = name;
	}


	public Personv2(Name name) {
		super();
		this.name = name;
	}


	private Name name;
}
