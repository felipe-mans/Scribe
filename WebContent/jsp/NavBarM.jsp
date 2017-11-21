<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="backend.JDBCQuery" %>
<%@ page import="databaseObjects.ClassMessage" %>
<%@ page import="databaseObjects.Classroom" %>
<%@ page import="databaseObjects.User" %>
<%@ page import="databaseObjects.UserDocument" %>
<%@ page import="java.util.Vector" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Scribe</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Member.css">
		<script>
			var socket;
			var requestButton = document.getElementById("addClass");
			function connectToServer() {
				socket = new WebSocket("ws://localhost:8080/Scribe/discussion");
				socket.onopen = function(event) {}
				socket.onmessage = function(event) {
					document.getElementById("discussion").innerHTML += event.data + "<br />";
				}
				socket.onclose = function(event) {}
			}
			function sendMessage() {
				socket.send(document.discussionBoard.message.value);
				return false;
			}
			function sendRequest() {
				socket.send(" has requested to join this group! <button class=\"button\" id=\"acceptButton\">Accept</button>");
			}
		</script>

	</head>
	<body onload="connectToServer()">
	
	<div class="navBarDiv">
	<ul id="navBar">
		<div class="right">
			<div class="logOut">
			<li id="navBarRight"><a href="Welcome.jsp">Log out</a></li>
			</div>
			<div class="homepage">
			<li id="navBarLeft"><a href="MemberPage.jsp">Scribe</a></li>
			</div>
			<div class="addClass">
			<li id="navBarLeft"><a id="addClass">New Class</a></li>
			</div>
		</div>
		<div class="left">
			<li id="search">
			<form id="searchBar" action="Search.jsp">
			<input type="text" name="search" placeholder="Search..." id="searchBox">
			</form>
			</li>
		</div>
	</ul>
	</div>
	<div class="formBackground" id="formBox">
		<div class="formContent" id="newClassForm">
			<form class="form" id="addClassform" action="ClassPage.jsp">
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