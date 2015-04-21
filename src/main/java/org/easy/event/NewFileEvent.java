package org.easy.event;

import java.io.File;
import java.io.Serializable;

public class NewFileEvent implements Serializable{

	private static final long serialVersionUID = 8244229216737621902L;

	public NewFileEvent(File file){
		this.file = file;
	}
	
	private final File file;

	public File getFile() {
		return file;
	}
}
