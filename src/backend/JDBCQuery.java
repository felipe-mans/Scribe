package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.jdt.internal.compiler.ast.Statement;

public class JDBCQuery {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Scribe";

	// Select statements
	private final static String selectUser = "SELECT * FROM Users WHERE username=?";

	// Insert statements
	private final static String addUser = "INSERT INTO Users(username, fname, lname, gamesPlayed, email) VALUES(?, ?, ?, ?, ?)";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	Connection conn = null;
	Statement stmt = null;

	public JDBCQuery() {
		// Register JDBC driver
		try {
			new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addUser(String username, String password, String fname, String lname, String email) {
		try {
			PreparedStatement ps = conn.prepareStatement(addUser);
			ps.setString(1, username);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, password);
			ps.setString(5, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getName(String username) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectUser);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return result.getString("fname") + result.getString("lname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean doesUserExist(String username) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectUser);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isCorrectPassword(String username, String password) {
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * from Users where username='" + username + "'");
			while (result.next()) {
				return password.equals(result.getString("userPass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Question getQuestion(Question.Difficulty difficulty) {

	public static void main(String[] args) {
		JDBCQuery Q = new JDBCQuery();
		Q.connect();

	}
}
