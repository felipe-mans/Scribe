<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
boolean signedIn = (boolean)request.getSession().getAttribute("signedIn");
User currUser = (User)request.getSession().getAttribute("currUser");
if(signedIn) {%>
	<%@include file="NavBarM.jsp"%>
<%} else {%>
	<%@include file="NavBarG.jsp"%>
<%}%>
<!--  the start tags here that are commented out are included in the nav bar JSP's
<html>
	<body> -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Search.css">
		<div class = "searchTitle">
			<h1>Search Results for <%= request.getParameter("search")%> </h1>
		</div>
		<div class = "searchResults">
		</div>
	</body>
</html>