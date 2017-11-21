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
			var requestButton = document.getElementById("requestButton");
			var requestButton = document.getElementById("addClass");
			function connectToServer() {
				socket = new WebSocket("ws://localhost:8080/Scribe/discussion");
				socket.onmessage = function(event) {
					document.getElementById("discussion-menu").innerHTML = event.data + "<br />" + document.getElementById("discussion-menu").innerHTML;
				}
			}
			function sendMessage() {
				var uname = document.getElementById("username").innerHTML;
				var classId = document.getElementById("classId").innerHTML;
				var userId = document.getElementById("userId").innerHTML;
				socket.send(classId + " " + userId + " " + uname + ": " + document.discussionBoard.message.value);
				document.getElementByName("message").value = "";
				return false;
			}
			function sendRequest() {
				var uname = document.getElementById("username").innerHTML;
				var classId = document.getElementById("classId").innerHTML;
				var userId = document.getElementById("userId").innerHTML;
				socket.send("REQUEST " + classId + " " + userId + " " + uname + " has requested to join this group! <noscript id=\"requestName\">" + uname + "</noscript><noscript id=\"requestId\">" + userId + "</noscript><button class=\"button\" id=\"acceptButton\" onclick=\"acceptRequest()\">Accept</button>");
			}
			function acceptRequest(u_name, user_Id) {
				var classId = document.getElementById("classId").innerHTML;
				var acceptButton = document.getElementById("acceptButton");
				socket.send("ACCEPT " + document.getElementById("requestName").innerHTML + " " + classId + " " + document.getElementById("requestId").innerHTML);
				location.reload();
			}
			function joinClass() {
				var classId = document.getElementById("classId").innerHTML;
				var userId = document.getElementById("userId").innerHTML;
				socket.send("JOIN " + classId + " " + userId);
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
			<div class="formTitle">
				<div class="letter">
					N
				</div>
				<div class="words">
					New Class
				</div>
			</div>
			<form class="form" id="addClassform" action="${pageContext.request.contextPath}/ClassCreateServlet">
				<input type="text" id="classname" name="classname" placeholder="Title"> <br>
				<div class="buttons">
					<input type="radio" id="private" name="privacy" value="true" checked>Private<br>
					<input type="radio" id="public" name="privacy" value="false">Public
				</div>
				<input type="submit" id="enter" value="Create Class">
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