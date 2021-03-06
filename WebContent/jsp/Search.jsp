<!-- Scribe

	Vincent Rodriguez
	Micahel Takla
	Felipe Mansilla
	Noah Feldmen
	
 -->

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
		<div class="box" id="search-list">
			<div class="box" id="search-title">
				Search Results for <%= request.getParameter("search")%>
			</div>
			<div class="vertical-list" id="search-menu">
				<div class="box" id="searchItem">
				<%
					Classroom classroom = JDBCQuery.getClassFromClassname(request.getParameter("search"));
					if(classroom != null)
					{
				%>
					<div id="classname">
						<form action="${pageContext.request.contextPath}/GoToClassServlet">
							<input type= "text" name="classroom" value=<%=classroom.getClassname()%> class="hidden"/> <!-- need to hide this field -->
							<input id= "classButton" type="submit" name="submit" value=<%=classroom.getClassname()%>></input>
						</form>
					</div>
					<div id="members">
						Members: <%=JDBCQuery.getUsersEnrolledInClass(classroom.getClassID()).size() %>
					</div>
					<div id="privacy">
					<%
						if(classroom.isPrivate())
						{
					%>
						This class is: Private.
					<%
						} else {
					%>
						This class is: Public.
					<%
						}
					%>
					</div>
				<%
					} 
				%>
				</div>
			</div>
		</div>
	</body>
</html>