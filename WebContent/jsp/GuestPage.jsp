<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="NavBarG.jsp"%>
<%
request.getSession().setAttribute("currUser", JDBCQuery.getUserByUsername("Guest"));
request.getSession().setAttribute("signedIn", false);
%>
<!--  the start tags here that are commented out are included in the nav bar JSP's
<html>
	<body> -->
		<h1>Welcome to Scribe!</h1>
		<h3>Sign up today to:</h3>
		<ul>
			<li>Join class groups</li>
			<li>Create class groups</li>
			<li>Upload/Share class resources</li>
			<li>Keep your classes organized</li>
			<li>Improve your grades!</li>
		</ul>
	</body>
</html>