package databaseObjects;

public class Classroom {

	private String mClassname;
	boolean mIsPrivate;

	public Classroom(String classname, boolean isPrivate) {
		this.mClassname = classname;
		this.mIsPrivate = isPrivate;
	}

	public String getClassname() {
		return this.mClassname;
	}

	public boolean isPrivate() {
		return this.mIsPrivate;
	}
}
