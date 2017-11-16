<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div id = "login_container">
				<p id= "form_header"> Please login with your username and password. </p>
				<div class=error_message id = "error_message"> 
					<% if (request.getAttribute("error") != null){ %>
						ERROR: <%= (String) request.getAttribute("error") %>
					<% } %>
				</div>
 				<form id="loginform" method="GET" action="${pageContext.request.contextPath}/">
 					<p>Username</p>
					<input type="text" name="username" id = "username">
					<br />
					<p>Password</p>
					<input type="text" name="password" id = "password">
					<br/>
					<input type="submit" value="Login" id="login" >
				</form>
				<br/>
				<div id="linkbox">
					<a href="${pageContext.request.contextPath}/">
						<button class="link">Create an Account</button>
					</a>
					<br/>
					<a href="${pageContext.request.contextPath}/">
						<button class="link">Enter as Guest</button>
					</a>
				</div>
			</div>




</body>
</html>