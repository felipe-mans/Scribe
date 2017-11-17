package databaseObjects;

public class User {

	private String mFirstname;
	private String mLastname;
	private String mUsername;
	private String mPassword;
	private String mEmail;

	public User(String firstname, String lastname, String username, String password, String email) {
		this.mFirstname = firstname;
		this.mLastname = lastname;
		this.mUsername = username;
		this.mPassword = password;
		this.mEmail = email;
	}

	public String getFirstname() {
		return this.mFirstname;
	}

	public String getLastname() {
		return this.mLastname;
	}

	public String getUsername() {
		return this.mUsername;
	}

	public String getPassword() {
		return this.mPassword;
	}

	public String getEmail() {
		return this.mEmail;
	}

	public String getFullname() {
		return this.getFirstname() + " " + this.getLastname();
	}

}
