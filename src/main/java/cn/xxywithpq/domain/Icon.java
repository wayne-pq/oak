package cn.xxywithpq.domain;

import java.io.Serializable;

public class Icon implements Serializable {

	private static final long serialVersionUID = 3579068848070332393L;

	byte[] files;

	public Icon(byte[] files) {
		super();
		this.files = files;
	}

	public Icon() {
		super();
	}

	public byte[] getFiles() {
		return files;
	}

	public void setFiles(byte[] files) {
		this.files = files;
	}

}
