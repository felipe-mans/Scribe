package databaseObjects;

public class Message {
	private int mClassID;
	private int mUserID;
	private int mLevel;
	private String mContent;

	public Message(int classid, int userid, int level, String content) {
		this.mClassID = classid;
		this.mUserID = userid;
		this.mLevel = level;
		this.mContent = content;
	}

	// GETTERS

	public int getClassID() {
		return this.mClassID;
	}

	public int getUserID() {
		return this.mUserID;
	}

	public int getLevel() {
		return this.mLevel;
	}

	public String getContent() {
		return this.mContent;
	}

}
