<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Scribe</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Member.css">
	</head>
	<body>
	
	<ul id="navBar">
		<li id="navBarRight"><a href="Welcome.jsp">Log out</a></li>
		<li id="navBarRight"><a id="search">Search</a></li>
		
		<li id="navBarLeft"><a href="MemberPage.jsp">S</a></li>
		<li id="navBarLeft"><a id="addClass">New Class</a></li>
	</ul>
	<div class="formBackground" id="formBox">
		<div class="formContent" id="newClassForm">
			<form class="form" id="addClassform" action="MemberPage.jsp">
				<h4>Create a New Class</h4> <br>
				<input id="public" name="public" type="radio">Public
				<input id="private" name="private" type="radio">Private<br><br>
				<input id="Title" name="title" placeholder="Title" type="text" class="text"> <br>
				<textarea id="inviteMembers" name="inviteMembers" placeholder="Invite Members by Email" form="addClassForm"></textarea><br><br>
				<input id="enter" type="submit" value="Create Class">
			</form>
		</div>
	</div>
	
	<script>
	var formBox = document.getElementById("formBox");
	
	var newClassForm = document.getElementById("newClassForm");
	
	var newClass = document.getElementById("addClass");
	
	newClass.onclick = function() {
		formBox.style.display = "block";
		newClassForm.style.display = "block";
	}
	
	window.onclick = function(event) {
	    if (event.target == formBox) {
	        formBox.style.display = "none";
	        loginBox.style.display = "none";
	        registerBox.style.display = "none";
	    }
	}
	</script>