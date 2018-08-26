package com.in28minutes.rest.services.restfulwebservices.helloWorld;

public class HelloWorldBean {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HelloWorldBean() {
	}

	public HelloWorldBean(String message) {
		this.message=message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}
