<!-- Scribe

	Vincent Rodriguez
	Micahel Takla
	Felipe Mansilla
	Noah Feldmen
	
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%User currUser = (User)request.getSession().getAttribute("currUser");%>
<%@include file="NavBarM.jsp"%>
<!--  the start tags here that are commented out are included in the nav bar JSP's
<html>
	<body>  -->
	
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/MemberPage.css">
		
		<div class= "cpBackdrop" id= "cpBackdrop">
		
			<p id=classPageWelcome> Welcome, <%=currUser.getUsername()%></p>
	
			<div id="classes" class="classes">
				<p id= "classesHeader">My Classes</p> <hr />
				<div class="box" id="classes-list">
					<div class="vertical-menu" id="classes-menu">
					<%
						Vector<Classroom> myClasses = JDBCQuery.getUserEnrollments(currUser.getUserID());
						for(Classroom classroom:myClasses) {
					%>
						<form id= "classItem" action="${pageContext.request.contextPath}/GoToClassServlet">
							<input id= "classHide" type="text" name="classroom" value=<%=classroom.getClassname()%>> <!-- need to hide this field -->
							<input id= "classButton" type="submit" name="submit" value=<%=classroom.getClassname()%>>
						</form>
					<% 
						}
					%>
					</div>

				
				
				<div class="enrollForm">
				<form id="enrollForm" action="Search.jsp">
    				<input id= "enroll" type="submit" value="Enroll">
				</form>
				</div>
				</div>
				
			</div>
		
			<div id="documents" class="documents">
				<p id="documentsHeader">My Documents</p> <hr />
				<div class="box" id="documents-list">
					<div class="vertical-menu" id="documents-menu">
				<%
					Vector<UserDocument> myDocuments = JDBCQuery.getUserDocuments(currUser.getUserID());
					for(UserDocument document:myDocuments) {
				%>
					<div class="<%=document.getExtension()%>">
						<p><a href="ViewFile.jsp?id=<%=document.getDocID()%>"><%=document.getName()%></a></p>
					</div>
				<% 
					}
				%>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>