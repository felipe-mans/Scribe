package backend;

import java.sql.*;

import org.eclipse.jdt.internal.compiler.ast.Statement;

import databaseObjects.User;

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
	private final static String getUserDocuments = "SELECT * FROM Documents WHERE userID=?";

	// Enrollments
	private final static String getUsersEnrolledInClass = "SELECT * FROM Enrollments WHERE classID=?";
	private final static String getUserEnrollments = "SELECT * FROM Enrollments WHERE userID=?";

	// Uploads
	private final static String getClassUploads = "SELECT * FROM Uploads WHERE classID=?";

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

	// UPDATE statements

	// Users
	private final static String updateUserFirstname = "UPDATE Users SET firstname=? WHERE userID=?";

	private final static String updateUserLastname = "UPDATE Users SET lastame=? WHERE userID=?";

	private final static String updateUserUsername = "UPDATE Users SET username=? WHERE userID=?";

	private final static String updateUserPassword = "UPDATE Users SET password=? WHERE userID=?";

	private final static String updateUserEmail = "UPDATE Users SET email=? WHERE userID=?";

	// Classes
	private final static String updateClassname = "UPDATE Classes SET classname=? WHERE classID=?";

	private final static String updateClassPrivacy = "UPDATE Classes SET private=? WHERE classID=?";

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

	/**
	 * Add a new user to database
	 * 
	 * @param fname
	 * @param lname
	 * @param username
	 * @param password
	 * @param email
	 */
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

	// user UPDATE methods

	// TODO
	public void updateUserFirstname(String newFirstname) {

	}

	// TODO
	public void updateUserLastname(String newLastname) {

	}

	// TODO
	public void updateUserUsername(String newUsername) {

	}

	// TODO
	public void updateUserPassword(String newPassword) {

	}

	// TODO
	public void updateUserEmail(String newEmail) {

	}

	// CLASS METHODS

	public void addClass(String classname, boolean isPrivate) {
		try {
			PreparedStatement ps = conn.prepareStatement(addClass);
			ps.setString(1, classname);
			ps.setBoolean(2, isPrivate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get classname using classID
	 * 
	 * @param classID
	 * @return
	 */
	public String getClassFromID(int classID) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectClassByClassID);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return result.getString("classname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return whether class is private or not
	 * 
	 * @param classID
	 * @return true if private else false
	 */
	public boolean isClassPrivate(int classID) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectClassByClassID);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return result.getString("private");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// class UPDATE methods

	/**
	 * Assume classID exists at this point Reverses class setting from private
	 * to public or vice versa
	 * 
	 * @param classID
	 * @return
	 */
	public boolean updateClassPrivacy(int classID) {

		boolean newPrivacySetting = !(this.isClassPrivate(classID));

		try {
			PreparedStatement ps = conn.prepareStatement(updateClassPrivacy);
			ps.setBoolean(1, newPrivacySetting);
			ps.setInt(2, classID);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// DOCUMENT METHODS

	/**
	 * return vector off all docIDs associated with a given userID
	 */
	public vector<Integer> getUserDocuments(int userID) {

		vector<Integer> userDocuments = new vector<>();

		try {
			PreparedStatement ps = conn.prepareStatement(getUserDocuments);
			ps.setInt(1, userID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				userDocuments.pushback(result.getInt("docID"));
			}

			return userDocuments;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// ENROLLMENT METHODS

	/**
	 * return vector of userIDs associated with a classID
	 */
	public vector<Integer> getUsersEnrolledInClass(int classID) {

		vector<Integer> usersInClass = new vector<>();

		try {
			PreparedStatement ps = conn.prepareStatement(getUsersEnrolledInClass);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				usersInClass.pushback(result.getInt("userID"));
			}

			return usersInClass;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * return vector of classIDs associated with userID
	 */
	public vector<Integer> getUserEnrollments(int userID) {

		vector<Integer> enrollment = new vector<>();

		try {
			PreparedStatement ps = conn.prepareStatement(getUserEnrollments);
			ps.setInt(1, userID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				enrollment.pushback(result.getInt("classID"));
			}

			return enrollment;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// UPLOAD METHODS

	/**
	 * return vector of all docIDs associated with a given classID
	 * 
	 * @param classID
	 * @return
	 */
	public vector<Integer> getClassUploads(int classID) {

		vector<Integer> classDocuments = new vector<>();

		try {
			PreparedStatement ps = conn.prepareStatement(getClassUploads);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				classDocuments.pushback(result.getInt("docID"));
			}

			return classDocuments;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// MESSAGE METHODS

	/**
	 * return vector of all messages posted in a class's discussion board
	 * 
	 * @param classID
	 * @return
	 */
	public vector<Integer> getMessagesFromClass(int classID) {

		vector<Integer> classMessages = new vector<>();

		try {
			PreparedStatement ps = conn.prepareStatement(getMessagesFromClass);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				classMessages.pushback(result.getInt("messageID"));
			}

			return classMessages;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * update message content
	 * 
	 * @param messageID
	 * @param newContent
	 */
	public void updateMessage(int messageID, String newContent) {

		// TODO
		return;
	}

	public static void main(String[] args) {
		JDBCQuery Q = new JDBCQuery();
		Q.connect();

	}
}
