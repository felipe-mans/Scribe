<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
/* boolean signedIn = (boolean)request.getSession().getAttribute("signedIn");
User currUser = (User)request.getSession().getAttribute("currUser"); */
if(true) {%>
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
				<h1>Welcome to (INSERT CLASS NAME)</h1>
			</div>
			<div class = "creator">
				<h2>Created by: <!-- creator --></h2>
			</div>
		</div>
		
		<button onclick="sendRequest()" id="requestButton" name="requestButton">Request to Join Class</button>
		
		
		
		
		<script>document.getElementById("requestButton").style.display="none";</script>
		
		<%
			if(/*class is private and user is not in the class and user is not a guest*/ true) { %>
			<script>document.getElementById("requestButton").style.display="inline";</script>
		<%}%>
		
		<div id="members" class="members">
			<h3>Members</h3><hr />
			<% 
				//boolean isPublic = false; //get this variable from the database
				//if(isCreator || isPublic) {%>
				<!-- addMember button -->
				<%//}%>
			<ul>
			<li>This is a test</li>
			</ul>
		</div>
		
		<form name="discussionBoard" onsubmit="return sendMessage();">
			<input type="text" name="message" value="Type Here!" /><br />
			<input type="submit" name="submit" value="Send Message" />
		</form>
		
		<div id="discussion" class="discussion">
			<h3>Discussion Board</h3><hr />
			<ul>
			<li>This is a test</li>
			</ul>
		</div>
	
		<div id="resources" class="resources">
			<h3>Resources</h3><hr />
			<ul>
			<li>This is a test</li>
			</ul>
		</div>
		<!-- What is this for?
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
		-->
	</body>
</html>