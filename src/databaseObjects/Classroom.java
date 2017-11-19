package databaseObjects;

public class Classroom {

	private int mClassID;
	private String mClassname;
	private boolean mIsPrivate;

	public Classroom(int classid, String classname, boolean isPrivate) {
		this.mClassID = classid;
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
