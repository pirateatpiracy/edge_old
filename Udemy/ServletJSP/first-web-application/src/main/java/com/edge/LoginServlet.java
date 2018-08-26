package com.edge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edge.todo.ToDoService;

/*
 * Browser sends Http Request to Web Server
 * 
 * Code in Web Server => Input:HttpRequest, Output: HttpResponse
 * JEE with Servlets
 * 
 * Web Server responds with Http Response
 * 
 * 
 * //Java Platform, Enterprise Edition (Java EE) JEE6

//Servlet is a Java programming language class 
//used to extend the capabilities of servers 
//that host applications accessed by means of 
//a request-response programming model.

//1. extends javax.servlet.http.HttpServlet
//2. @WebServlet(urlPatterns = "/login.do")
//3. doGet(HttpServletRequest request, HttpServletResponse response)
//4. How is the response created?
 */

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	UserValidationService userValidationService=new UserValidationService();
	ToDoService toDoService=new ToDoService(); 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);		
	}	
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
			
			String name=request.getParameter("nameFromLoginJSP");
			String pass=request.getParameter("passwordFromLoginJSP");
			if(userValidationService.isUserValid(name, pass)) {
			request.setAttribute("name", name);
			response.sendRedirect("/todo.do");}
			
			else {
				request.setAttribute("error", "Wrong Credentials");
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);		}
		}
	

}