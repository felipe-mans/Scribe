package databaseObjects;

import java.io.File;

public class UserDocument {

	private int docID;
	private String mName;
	private File mFile;

	public UserDocument(int docID, String name, File file) {
		this.mName = name;
		this.mFile = file;
	}

	public int getDodID() {
		return this.docID;
	}

	public String getName() {
		return this.mName;
	}

	public File getFile() {
		return this.mFile;
	}
	
	public String getExtension() {
		String[] parts = mName.split("\\.");
		return parts[parts.length - 1];
	}
	
}
