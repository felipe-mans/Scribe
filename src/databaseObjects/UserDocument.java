package databaseObjects;

import java.io.File;

public class UserDocument {

	private String mName;
	private File mFile;

	public UserDocument(String name, File file) {
		this.mName = name;
		this.mFile = file;
	}

	public String getName() {
		return this.mName;
	}

	public File getFile() {
		return this.mFile;
	}

}
