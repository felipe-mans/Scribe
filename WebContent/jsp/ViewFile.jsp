<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="backend.JDBCQuery"%>
<%@ page import="databaseObjects.UserDocument"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.io.OutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>File</title>
	</head>
	<body>
		<%  
            String id = request.getParameter("id");
			System.out.println(id);
            Blob blob = null;
            byte[ ] fileData = null;
            try
            {    
            	UserDocument doc = JDBCQuery.getDocumentFromID(Integer.parseInt(id));
            	switch(doc.getExtension())
            	{
            		case "mp4": response.setContentType("video/mpeg");
            			break;
            		case "mp3": response.setContentType("audio/mpeg");
            			break;
            		case "pdf": response.setContentType("application/pdf");
            			break;
            	}
                response.setHeader("Content-Disposition", "inline");
                blob = doc.getBlob();
                fileData = blob.getBytes(1,(int)blob.length());
                response.setContentLength(fileData.length);
                OutputStream output = response.getOutputStream();
                output.write(fileData);
                output.flush();
                output.close();
            } catch (SQLException sqle) {System.out.println("sqle: " + sqle.getMessage());} 
        %>
	</body>
</html>