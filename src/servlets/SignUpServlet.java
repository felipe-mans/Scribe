package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.JDBCQuery;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Error Checking
		if (fname == null){
			request.setAttribute("error", "Error in RegisterServ: name para null");
			request.getRequestDispatcher("jsp/Welcome.jsp").forward(request, response);
			return;
		}
		else if (lname == null){
			request.setAttribute("error", "Error in RegisterServ: username para null");
			request.getRequestDispatcher("jsp/Welcome.jsp").forward(request, response);
			return;
		}
		else if (username == null){
			request.setAttribute("error", "Error in RegisterServ: username para null");
			request.getRequestDispatcher("jsp/Welcome.jsp").forward(request, response);
			return;
		}
		else if (email == null){
			request.setAttribute("error", "Error in RegisterServ: email para null");
			request.getRequestDispatcher("jsp/Welcome.jsp").forward(request, response);
			return;
		}
		else if (password == null){
			request.setAttribute("error", "Error in RegisterServ: password para null");
			request.getRequestDispatcher("jsp/Welcome.jsp").forward(request, response);
			return;
		} else if (fname.equals("") || lname.equals("") || username.equals("") || email.equals("") || password.equals("") ){
			request.setAttribute("error", "Fields cannot be empty");
			request.getRequestDispatcher("jsp/Welcome.jsp").forward(request, response);
			return;
		}
		
		if (JDBCQuery.doesUserExist(username)){
			request.setAttribute("error", "User already exists");
			request.getRequestDispatcher("jsp/Welcome.jsp").forward(request, response);
		}
		else{
			JDBCQuery.addUser(fname, lname, username, password, email);
			request.getSession().setAttribute("currUser", JDBCQuery.getUserByUsername(username));
			request.getSession().setAttribute("signedIn", true);
			response.sendRedirect("jsp/MemberPage.jsp");
		}
	}
}
