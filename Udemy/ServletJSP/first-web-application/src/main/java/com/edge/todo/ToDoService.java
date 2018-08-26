package com.edge.todo;

import java.util.ArrayList;
import java.util.*;

public class ToDoService {
	private static List<ToDo> todos=new ArrayList<ToDo>();
	static {
		todos.add(new ToDo("Learn Servlet Json"));
		todos.add(new ToDo("Learn Hibernate"));
		todos.add(new ToDo("Learn Microservices"));
	}
 
	public List<ToDo> retrieveToDos(){return todos;}
}
