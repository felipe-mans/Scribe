<!-- Scribe

	Vincent Rodriguez
	Micahel Takla
	Felipe Mansilla
	Noah Feldmen
	
 -->

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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/GuestPage.css">
		<div class="backdrop">
		<div class="welcomeSection">
		<p id="welcome">Welcome to Scribe!</p>
		</div>
		<h3>Sign up today to:</h3>
		<ul>
			<li>Join class groups</li>
			<li>Create class groups</li>
			<li>Upload/Share class resources</li>
			<li>Keep your classes organized</li>
			<li>Improve your grades!</li>
		</ul>
		</div>
	</body>
</html>