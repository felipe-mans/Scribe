package databaseObjects;

public class Class {

	private String mClassname;
	boolean mIsPrivate;

	public Class(String classname, boolean isPrivate) {
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
