<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%User currUser = (User)request.getSession().getAttribute("currUser");%>
<%@include file="NavBarM.jsp"%>
<!--  the start tags here that are commented out are included in the nav bar JSP's
<html>
	<body> -->
		
		<h1>Welcome <%=currUser.getUsername()%></h1>
	
		<div id="classes" class="classes">
			<h3>My Classes</h3> <hr />
			<div class="box" id="classes-list">
				<div class="vertical-menu" id="classes-menu">
				<%
					Vector<Classroom> myClasses = JDBCQuery.getUserEnrollments(currUser.getUserID());
					for(Classroom classroom:myClasses) {
				%>
					<form action="/GoToClassServlet">
						<%=classroom.getClassname()%>
						<input type="text" name="classroom" value=<%=classroom.getClassname()%>> <!-- need to hide this field -->
						<input type="submit" name="submit"/>
					</form>
				<% 
					}
				%>
				</div>
				
				<a href="Search.jsp">New Class</a>
			</div>
		</div>
		
		<div id="documents" class="documents">
			<h3>My Documents</h3> <hr />
			<div class="box" id="documents-list">
				<div class="vertical-menu" id="documents-menu">
				<%
					Vector<UserDocument> myDocuments = JDBCQuery.getUserDocuments(currUser.getUserID());
					for(UserDocument document:myDocuments) {
				%>
					
				<% 
					}
				%>
				</div>
			</div>
		</div>
	</body>
</html>