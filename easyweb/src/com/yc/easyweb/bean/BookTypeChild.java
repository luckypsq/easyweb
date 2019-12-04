package com.yc.easyweb.bean;

import java.io.Serializable;

public class BookTypeChild  extends BookType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String open;
	private String name;
	private int id;
	private int pId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
}
