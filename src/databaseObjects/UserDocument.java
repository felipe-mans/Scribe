package databaseObjects;

import java.io.File;

public class UserDocument {

	private int docID;
	private String mName;
	private String mExtension;
	private File mFile;

	public UserDocument(int docID, String name, String extension, File file) {
		this.mName = name;
		this.mExtension = extension;
		this.mFile = file;
	}

	public int getDodID() {
		return this.docID;
	}

	public String getName() {
		return this.mName;
	}

	public String getExtension() {
		return this.mExtension;
	}

	public File getFile() {
		return this.mFile;
	}

}
