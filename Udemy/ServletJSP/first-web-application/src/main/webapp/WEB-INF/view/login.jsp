<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<%-- <%System.out.println(" Achha"); %> --%>
<form action="/login.do" method="post">
	<p>
		<font color="red">${error}</font>
	</p>
	Enter your name <input type="text" name="nameFromLoginJSP">
	Password <input type="password" name="passwordFromLoginJSP"> <input
		type="submit" value="LogIn">
</form>
</html>