<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
boolean signedIn = false; //get this variable from the session
if(signedIn) {%>
	<%@include file="NavBarM.jsp"%>
<%} else {%>
	<%@include file="NavBarG.jsp"%>
<%}%>
<!--  the start tags here that are commented out are included in the nav bar JSP's
<html>
	<body> -->
		<div class = "titleBar">
			<div class = "logo">
			</div>
			<div class = "welcome">
				Welcome to <!-- className -->
			</div>
			<div class = "creator">
				Created by: <!-- creator -->
			</div>
		</div>
		<div class = "srButtons">
			<div class = "sButton">
			</div>
			<%
			boolean isCreator = false; //get this variable from the session
			if(isCreator) {%>
				<div class = "rButton">
			</div>
			<%}%>
		</div>
		<div class = "members">
			<div class = "membersTitle">
				Members
				<%
				boolean isPublic = false; //get this variable from the database
				if(isCreator || isPublic) {%>
				<!-- addMember button -->
				<%}%>
			</div>
			<div class = "membersList">
			</div>
		</div>
		<div class = "discussion">
			<div class = "discussionTitle">
				Discussion
				<!-- addPost button -->
			</div>
			<div class = "discussionList">
			</div>
			<div class = "discussionPost">
			</div>
		</div>
		<div class = "resources">
			<div class = "resourcesTitle">
				Resources
				<!-- addResource button -->
			</div>
			<div class = "resourcesList">
			</div>
		</div>
	</body>
</html>