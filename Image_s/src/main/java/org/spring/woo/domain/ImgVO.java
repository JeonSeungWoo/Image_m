package org.spring.woo.domain;

public class ImgVO {
	private int ino;
	private int bno;
	private String path;
	private String filename;
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "ImgVO [ino=" + ino + ", bno=" + bno + ", path=" + path + ", filename=" + filename + "]";
	}
	
	

}
