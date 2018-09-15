package com.edge.todo;

public class ToDo {

	private String name;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "ToDo [name=" + name + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public ToDo(String name) {
		super();
		this.name = name;
	}
}
