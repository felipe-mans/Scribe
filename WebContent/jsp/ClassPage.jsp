<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
boolean signedIn = (boolean)request.getSession().getAttribute("signedIn");
User currUser = (User)request.getSession().getAttribute("currUser");
Classroom currClass = (Classroom)request.getSession().getAttribute("currClass");
if(signedIn) {%>
	<%@include file="NavBarM.jsp"%>
<%} else {%>
	<%@include file="NavBarG.jsp"%>
<%}%>
<!--  the start tags here that are commented out are included in the nav bar JSP's
<html>
	<body> -->
		<noscript id ="username"><%=currUser.getUsername()%></noscript>
		<noscript id ="userId"><%=currUser.getUserID()%></noscript>
		<noscript id ="classId"><%=currClass.getClassID()%></noscript>
	
		<div class="box" id="title-bar">
			<div class = "logo">
			</div>
			<div class = "welcome">
				<h1>Welcome to <%= currClass.getClassname()%></h1>
			</div>
			<div class = "creator">
				<h2>Created by: <!-- creator --></h2>
			</div>
		</div>
			
		<div class="box" id="members-list">
			<div class="title" id="members-title">
				Members
			</div>
			<div class="vertical-menu" id="classes-menu">
				<%
					Vector<User> students = JDBCQuery.getUsersEnrolledInClass(currClass.getClassID());
				
					for(User student:students)
					{
				%>
					<p id="classmate"> <%= student.getUsername() %> </p>
				<%
					}					
				%>
			</div>
		</div>
		
		<div class="box" id="discussion-box">
			<div class="title" id="discussion-title">
				Discussion
			</div>
			<div class="vertical-menu" id="discussion-menu">
				<%
					Vector<ClassMessage> classMessages = JDBCQuery.getMessagesFromClass(currClass.getClassID());
				
					for(ClassMessage message:classMessages)
					{
				%>
					
				<%
					}
				%>
			</div>
			<form name="discussionBoard" onsubmit="return sendMessage();">
				<input type="text" name="message" placeholder="Type Here!" /><br />
				<input type="submit" name="submit" value="Send Message" />
			</form>
		</div>
	
		<div class="box" id="documents-list">
			<div class="title" id="resources-title">
				Resources
			</div>
			<div class="resourceButton">
				<form action="${pageContext.request.contextPath}/FileUploadServlet" method="POST" enctype="multipart/form-data" accept="mp3, mp4, docx">
					<input type = "file" name = "file"/>
					<input type = "submit" value = "Upload File" />
				</form>
			</div>
			<div class="vertical-menu" id="documents-menu">
				<%
					Vector<UserDocument> classDocuments = JDBCQuery.getClassUploads(currClass.getClassID());
				
					for(UserDocument document:classDocuments)
					{
						String ext = document.getExtension();
						if(ext.equals("mp3")) {							
				%> <audio controls> <source src="<%document.getName();%>" type="audio/mpeg"></audio> 
						<%}
						if(ext.equals("mp4")) {
				%> <video width="250" controls> <source src="<%document.getName();%>" type="video/mp4"></audio>			
						<%}
					}
				%>
			</div>
		</div>
		
		<button onclick="sendRequest()" id="requestButton" name="requestButton">Request to Join Class</button>
			
		<script>document.getElementById("requestButton").style.display="none";</script>
		
		<%
			if(/*class is private and user is not in the class and user is not a guest*/ true) 
			{ 
		%>
			<script>document.getElementById("requestButton").style.display="inline";</script>
		<%
			}
		%>
	</body>
</html>