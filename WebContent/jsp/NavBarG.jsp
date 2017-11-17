<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Scribe - Sign Up Today!</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Guest.css">
	</head>
	<body>
	
	<ul id="navBar">
		<li id="navBarOption"><a id="signUp">Sign Up</a></li>
		<li id="navBarOption"><a id="search">Search</a></li>
	</ul>
	<div class="formBackground" id="formBox">
		<div class="formContent" id="registerForm">
			<form class="form" action="Welcome.jsp">
				<input id="name" name="name" placeholder="Name" type="text" class="text"> <br>
				<input id="username" name="username" placeholder="Username" type="text" class="text"> <br>
				<input id="email" name="email" placeholder="Email" type="text" class="text"> <br>
				<input id="password" name="password" placeholder="Password" type="text" class="text"> <br> <br>
				<input id="enter" type="submit" value="Sign Up">
			</form>
		</div>
	</div>
	
	<script>
	var formBox = document.getElementbyId("formBox");
	var register = document.getElementById('registerForm');
	
	var signUp = document.getElementById("signUp");
	
	signUp.onclick = function() {
		formBox.style.display = "block";
		register.style.display = "block";
	}
	</script>