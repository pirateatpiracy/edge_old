package com.edge.passBy;

public class Project {
	
	int id;
	String name;
	String client;
	public Project() {
		super();
	}
	public Project(int id, String name, String client) {
		super();
		this.id = id;
		this.name = name;
		this.client = client;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
	

}
