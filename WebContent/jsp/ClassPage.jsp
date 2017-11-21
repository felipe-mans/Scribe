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
		<noscript id="username"><%=currUser.getUsername()%></noscript>
		<noscript id="userId"><%=currUser.getUserID()%></noscript>
		<noscript id="classId"><%=currClass.getClassID()%></noscript>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ClassPage.css">
	
	<div class="backdrop">
		<p id="welcome">Welcome to <%= currClass.getClassname()%></p>
		<p id="creator">Created by: <!-- creator --></p>
				
		<div class="box" id="membersList">
			<p id="membersTitle">Members</p> <hr />
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
		
		<div class="box" id="discussionBox">
			<p id="discussionTitle"> Discussion </p> <hr />
			<div class="vertical-menu" id="discussion-menu">
				<%
					Vector<User> requests = JDBCQuery.getUsersWithRequests(currClass.getClassID());
					for(int i = requests.size() - 1; i >=0; i--) { %>
						<p><%=requests.get(i).getUsername()%> has requested to join this group! <noscript id="requestName"><%=requests.get(i).getUsername()%></noscript><noscript id="requestId"><%=requests.get(i).getUserID()%></noscript><button class="button" id="acceptButton" onclick="acceptRequest()">Accept</button></p>
				<%	}
					Vector<ClassMessage> classMessages = JDBCQuery.getMessagesFromClass(currClass.getClassID());
					for(int i = classMessages.size()-1; i >= 0; i--)
					{
				%>
					<p><%=classMessages.get(i).getContent()%></p>
				<%
					}
				%>
			</div>
			<form name="discussionBoard" onsubmit="return sendMessage();">
				<input type="text" name="message" placeholder="Type Here!" /><br />
				<input type="submit" name="submit" value="Send Message" />
			</form>
		</div>
	
		<div class="box" id="documentsList">
			<p id="documentsTitle">Resources</p> <hr />
			<div class="resourceButton">
				<form action="${pageContext.request.contextPath}/FileUploadServlet" method="POST" enctype="multipart/form-data" accept="mp3, mp4, docx">
					<input type = "file" name = "file"/>
					<input type = "submit" value = "Upload File" />
				</form>
			</div>
			<div class="vertical-menu" id="documents-menu">
				<%
					Vector<UserDocument> classDocuments = JDBCQuery.getClassUploads(currClass.getClassID());
					System.out.println(classDocuments.size());
					for(UserDocument document:classDocuments)
					{ %>
						<p><a href="ViewFile.jsp?id=<%=document.getDocID()%>"><%=document.getName()%></a></p>
					<%}
				%>
			</div>
		</div>
		
		<button onclick="sendRequest()" id="requestButton" name="requestButton">Request to Join Class</button>
		
		</div>	
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