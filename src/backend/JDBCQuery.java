package backend;

import java.sql.*;

import org.eclipse.jdt.internal.compiler.ast.Statement;

public class JDBCQuery {

	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	private Statement stmt = null;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Scribe";

	// SELECT statements

	// Users
	private final static String selectUserByUsername = "SELECT * FROM Users WHERE username=?";
	private final static String selectUserByUserID = "SELECT * FROM Users WHERE userID=?";
	private final static String selectPassword = "SELECT password FROM User WHERE username=?";

	// Classes
	private final static String selectClassByClassID = "SELECT * FROM Classes WHERE classID=?";
	private final static String selectClassByClassname = "SELECT * FROM Classes WHERE classname=?";

	// Documents
	private final static String getUserUploads = "SELECT * FROM Documents WHERE userID=?";

	// Enrollments
	private final static String getUsersEnrolledInClass = "SELECT * FROM Enrollments WHERE classID=?";
	private final static String getUserEnrollments = "SELECT * FROM Enrollments WHERE userID=?";

	// Uploads
	private final static String getClassDocuments = "SELECT * FROM Uploads WHERE classID=?";

	// Messages
	private final static String getMessagesFromClass = "SELECT * FROM Messages WHERE classID=?";

	// INSERT statements

	// Users
	private final static String addUser = "INSERT INTO Users(fname,  email) VALUES(?, ?, ?, ?, ?)";

	// Classes
	private final static String addClass = "INSERT INTO Classes(classname, private) VALUES(?,?)";

	// Documents
	private final static String addDocument = "INSERT INTO Documents(userID, file) VALUES(?,?)";

	// Enrollments
	private final static String addEnrollment = "INSERT INTO Enrollments(classID, userID) VALUES(?,?)";

	// Uploads
	private final static String addUpload = "INSERT INTO Uploads(classID, docID) VALUES(?,?)";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public JDBCQuery() {
		// Register JDBC driver
		try {
			new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Scribe?user=root&password=root&useSSL=false");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if (ps != null) {
				ps = null;
			}
		} catch (SQLException sqle) {
			System.out.println("connection close error");
			sqle.printStackTrace();
		}
	}

	public void stop() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// USER METHODS

	public void addUser(String fname, String lname, String username, String password, String email) {
		try {
			PreparedStatement ps = conn.prepareStatement(addUser);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * gets User object using username
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectUserByUsername);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return new User(result.getString("fname"), result.getString("lname"), result.getString("username"),
						result.getString("password"), result.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns User object using userID
	 * 
	 * @param userID
	 * @return
	 */

	public User getUserByUserID(int userID) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectUserByUserID);
			ps.setInt(1, userID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return new User(result.getString("fname"), result.getString("lname"), result.getString("username"),
						result.getString("password"), result.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * does username currently exist
	 * 
	 * @param username
	 * @return true if username exists, else false
	 */
	public boolean doesUserExist(String username) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectUserByUsername);
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

	/**
	 * validates password entry to username
	 * 
	 * @param username
	 * @param password
	 * @return true if passwords match, else false
	 */
	public static boolean validate(String username, String password) {
		connect();
		try {
			ps = conn.prepareStatement(selectPassword);
			ps.setString(1, username);
			rs = ps.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				if (password.equals(rs.getString("password"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException in function \"validate\"");
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// CLASS METHODS

	public static void main(String[] args) {
		JDBCQuery Q = new JDBCQuery();
		Q.connect();

	}
}
