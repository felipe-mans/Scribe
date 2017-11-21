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
			<ul>
			<li><a href="ClassPage.jsp">This is a test</a></li>
			</ul>
		</div>
		
		<div id="documents" class="documents">
			<h3>My Documents</h3> <hr />
			<ul>
			<li>This is a test</li>
			</ul>
		</div>
	
	</body>
</html>