<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome to Scribe!</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Welcome.css">
		<!-- <script type="text/javascript" src="./js/test.js"></script> -->
	</head>
	<body>
		<div class="formBackground" id="formBox">	
			<div class="formContent" id="loginForm">
				<form class="form" action="MemberPage.jsp">
					<h4>Login to your account</h4> <br>
					<input id="username" name="username" placeholder="Username" type="text" class="text"> <br>
					<input id="password" name="password" placeholder="Password" type="text" class="text"> <br><br>
					<input id="enter" type="submit" value="Sign in">
				</form>
			</div>
			<div class="formContent" id="registerForm">
				<form class="form" action="Welcome.jsp">
					<h4>Sign up for an account</h4>
					<input id="fname" name="fname" placeholder="First Name" type="text" class="text"> <br>
					<input id="lname" name="lname" placeholder="Last Name" type="text" class="text"> <br>
					<input id="username" name="username" placeholder="Username" type="text" class="text"> <br>
					<input id="email" name="email" placeholder="Email" type="text" class="text"> <br>
					<input id="password" name="password" placeholder="Password" type="text" class="text"> <br><br>
					<input id="enter" type="submit" value="Sign Up">
				</form>
			</div>
		</div>
		<div class="box" id="welcomeSection">
			<div class="bar" id="welcomeTitle">
				<h1>Welcome to Scribe</h1>
			</div>	
		</div>
		<div class="bar" id="buttonSection">
			<div class="box" id="message">
			</div>
			<div class="box" id="buttons">
				<ul class="list" id="buttonList">
				<li id="button"><button class="button" id="guestButton">Continue as Guest</button></li>
				<li id="button"><button class="button" id="loginButton">Login</button></li>
				<li id="button"><button class="button" id="registerButton">Sign Up</button></li>
				</ul>
			</div>
		</div>
		<script>
		//Divs
		var formBox = document.getElementById("formBox");
		var loginBox = document.getElementById("loginForm");
		var registerBox = document.getElementById("registerForm");
		
		//Buttons
		var login = document.getElementById("loginButton");		
		var register = document.getElementById("registerButton");		
		var guest = document.getElementById("guestButton");

		login.onclick = function() {
			formBox.style.display = "block";
		    loginBox.style.display = "block";
		}
		
		register.onclick = function() {
			formBox.style.display = "block";
			registerBox.style.display = "block";
		}
		
		guest.onclick = function() {
			window.location.href = "GuestPage.jsp";
		}
		
		window.onclick = function(event) {
		    if (event.target == formBox) {
		        formBox.style.display = "none";
		        loginBox.style.display = "none";
		        registerBox.style.display = "none";
		    }
		}
		</script>
	</body>
</html>