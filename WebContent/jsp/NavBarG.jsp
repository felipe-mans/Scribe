<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="backend.JDBCQuery"%>
 <%@ page import="databaseObjects.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Scribe - Sign Up Today!</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Guest.css">
	</head>
	<body>
	
	<div class="navBarDiv">
	<ul id="navBar">
		<div class="formDiv">
		<li id="search">
		<form id="searchBar" action="Search.jsp">
		<input type="text" name="search" placeholder="Search..." id="searchBox">
		</form>
		</li>
		</div>
		<div class="signUpButton">
		<li id="navBarOption"><a id="signUp">Sign Up</a></li>
		</div>
	</ul>
	</div>
	<div class="formBackground" id="formBox">
		<div class="formContent" id="registerForm">
			<form class="form" action="Welcome.jsp">
				<p id="formTitle">Sign up for an account</p> <br>
					<input id="fname" name="fname" placeholder="First Name" type="text" class="text"> <br>
					<input id="lname" name="lname" placeholder="Last Name" type="text" class="text"> <br>
					<input id="username" name="username" placeholder="Username" type="text" class="text"> <br>
					<input id="email" name="email" placeholder="Email" type="text" class="text"> <br>
					<input id="password" name="password" placeholder="Password" type="password" class="text"> <br><br>
					<input id="enter" type="submit" value="Sign Up">
			</form>
		</div>
	</div>
	
	<script>
	var signUp = document.getElementById("signUp");
	var formBox = document.getElementById("formBox");
	var form = document.getElementById("registerForm");
	
	signUp.onclick = function() {
		formBox.style.display = "block";
		form.style.display = "block";
	}
	
	window.onclick = function(event) {
	    if (event.target == formBox) {
	        formBox.style.display = "none";
	        form.style.display = "none";
	    }
	}
	</script>