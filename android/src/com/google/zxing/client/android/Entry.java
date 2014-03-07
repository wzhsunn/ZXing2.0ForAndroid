package com.google.zxing.client.android;

import java.io.Serializable;


public class Entry implements Serializable{

    private static final long serialVersionUID = -7060210544600464481L;   

	private int index;
	private String name;
	private String value;
	public Entry(int index, String name, String value){
		this.index = index;
		this.name = name;
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
	

}
