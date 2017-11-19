package backend;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.eclipse.jdt.internal.compiler.ast.Statement;

import databaseObjects.ClassMessage;
import databaseObjects.Classroom;
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
	private final static String getDocumentByDocumentID = "SELECT * FROM Documents WHERE docID=?";

	// Enrollments
	private final static String getUsersEnrolledInClass = "SELECT * FROM Enrollments WHERE classID=?";
	private final static String getUserEnrollments = "SELECT * FROM Enrollments WHERE userID=?";

	// Uploads
	private final static String getClassUploads = "SELECT * FROM Uploads WHERE classID=?";

	// Messages
	private final static String getMessagesFromClass = "SELECT * FROM Messages WHERE classID=?";
	private final static String getMessageFromID = "SELECT * FROM Messages WHERE messageID=?";

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

	// Messages

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
			e.printStackTrace();
		} catch (SQLException e) {
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

	public void updateUserFirstname(String newFirstname) {
		try {
			PreparedStatement ps = conn.prepareStatement(updateUserFirstname);
			ps.setString(1, newFirstname);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUserLastname(String newLastname) {
		try {
			PreparedStatement ps = conn.prepareStatement(updateUserLastname);
			ps.setString(1, newLastname);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUserUsername(String newUsername) {
		try {
			PreparedStatement ps = conn.prepareStatement(updateUserUsername);
			ps.setString(1, newUsername);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUserPassword(String newPassword) {
		try {
			PreparedStatement ps = conn.prepareStatement(updateUserPassword);
			ps.setString(1, newPassword);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUserEmail(String newEmail) {
		try {
			PreparedStatement ps = conn.prepareStatement(updateUserEmail);
			ps.setString(1, newEmail);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// CLASS METHODS

	/**
	 * Add new class to Classes
	 * 
	 * @param classname
	 * @param isPrivate
	 */
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
	 * Does class exist?
	 * 
	 * @param classname
	 * @return true if yes, else no
	 */
	public boolean doesClassExist(String classname) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectClassByClassname);
			ps.setString(1, classname);
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
	 * Get classname using classID
	 * 
	 * @param classID
	 * @return
	 */
	public Classroom getClassFromID(int classID) {
		try {
			PreparedStatement ps = conn.prepareStatement(selectClassByClassID);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return new Classroom(result.getString("classname"), result.getBoolean("private"));
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
				return result.getBoolean("private");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// class UPDATE methods

	/**
	 * Assume classID exists at this point Reverses class setting from private
	 * to public or vice versa
	 * 
	 * @param classID
	 * @return
	 */
	public void updateClassPrivacy(int classID) {
		boolean newPrivacySetting = !(this.isClassPrivate(classID));
		try {
			PreparedStatement ps = conn.prepareStatement(updateClassPrivacy);
			ps.setBoolean(1, newPrivacySetting);
			ps.setInt(2, classID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// TODO

	// DOCUMENT METHODS

	// GetDocumentFromID

	// TODO

	/**
	 * Returns the LONGBLOB file associated with a docID
	 * 
	 * @param docID
	 * @return
	 */

	public File getDocumentFile(int docID) {
		try {
			PreparedStatement ps = conn.prepareStatement(getDocumentByDocumentID);
			ps.setInt(1, docID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				// TODO
				// need to verify how to retrieve longblob
				Blob blob = result.getBlob("file");
				File file = new File("here");
				InputStream in = blob.getBinaryStream();
				OutputStream out = FileOutputStream(file);
				byte[] buff = blob.getBytes(1, (int) blob.length());
				out.write(buff);
				out.close();
				return file;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * return vector off all docIDs associated with a given userID
	 */
	private Vector<Integer> getUserDocuments2(int userID) {

		Vector<Integer> userDocuments = new Vector<Integer>();

		try {
			PreparedStatement ps = conn.prepareStatement(getUserDocuments);
			ps.setInt(1, userID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				userDocuments.add(result.getInt("docID"));
			}

			return userDocuments;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// Vector<UserDocument>

	public void getUserDocuments(int userID) {
		// TODO
	}

	// ENROLLMENT METHODS

	/**
	 * return vector of userIDs associated with a classID
	 */
	private Vector<Integer> getUsersEnrolledInClass2(int classID) {

		Vector<Integer> usersInClass = new Vector<Integer>();

		try {
			PreparedStatement ps = conn.prepareStatement(getUsersEnrolledInClass);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				usersInClass.add(result.getInt("userID"));
			}

			return usersInClass;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * return Vector of Users from classID
	 * 
	 * @param classID
	 * @return
	 */

	public Vector<User> getUsersEnrolledInClass(int classID) {

		Vector<User> enrolledUsers = new Vector<>();

		Vector<Integer> userIDs = this.getUsersEnrolledInClass2(classID);

		for (Integer id : userIDs) {
			enrolledUsers.add(this.getUserByUserID(id));
		}

		return enrolledUsers;

	}

	/**
	 * return vector of classIDs associated with userID
	 */
	private Vector<Integer> getUserEnrollments2(int userID) {

		Vector<Integer> enrollment = new Vector<Integer>();

		try {
			PreparedStatement ps = conn.prepareStatement(getUserEnrollments);
			ps.setInt(1, userID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				enrollment.add(result.getInt("classID"));
			}

			return enrollment;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Vector<Classroom> getUserEnrollments(int userID) {

		Vector<Classroom> userClasses = new Vector<>();

		Vector<Integer> classIDs = this.getUserEnrollments2(userID);

		for (Integer id : classIDs) {
			userClasses.add(this.getClassFromID(id));
		}

		return userClasses;
	}

	// UPLOAD METHODS

	/**
	 * return vector of all docIDs associated with a given classID
	 * 
	 * @param classID
	 * @return
	 */
	public Vector<Integer> getClassUploads2(int classID) {

		Vector<Integer> classDocuments = new Vector<Integer>();

		try {
			PreparedStatement ps = conn.prepareStatement(getClassUploads);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				classDocuments.add(result.getInt("docID"));
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
	private Vector<Integer> getMessagesFromClass2(int classID) {

		Vector<Integer> classMessages = new Vector<Integer>();

		try {
			PreparedStatement ps = conn.prepareStatement(getMessagesFromClass);
			ps.setInt(1, classID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				classMessages.add(result.getInt("messageID"));
			}

			return classMessages;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public ClassMessage getMessageFromID(int messageID) {

		try {
			PreparedStatement ps = conn.prepareStatement(getMessageFromID);
			ps.setInt(1, messageID);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				// int classid, int userid, int level, String content
				return new ClassMessage(result.getInt("classID"), result.getInt("userID"), result.getInt("level"),
						result.getString("content"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * returns vector of message classes
	 * 
	 * @param classID
	 * @return
	 */
	public Vector<ClassMessage> getMessagesFromClass(int classID) {

		Vector<ClassMessage> classMessages = new Vector<>();

		Vector<Integer> messageIDs = this.getMessagesFromClass2(classID);

		for (Integer id : messageIDs) {
			classMessages.add(this.getMessageFromID(id));
		}

		return classMessages;
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
