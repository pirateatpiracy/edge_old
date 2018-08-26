package com.edge.todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edge.todo.ToDoService;



@WebServlet(urlPatterns = "/todo.do")
public class ToDoServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	ToDoService toDoService=new ToDoService(); 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("todos", toDoService.retrieveToDos());
		request.setAttribute("name", request.getParameter("name"));
		request.getRequestDispatcher("/WEB-INF/view/todo.jsp").forward(request, response);	
		
	}	
		
	
	

}