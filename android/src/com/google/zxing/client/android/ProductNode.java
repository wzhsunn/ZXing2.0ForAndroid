package com.google.zxing.client.android;

import java.io.Serializable;
import java.util.List;

public class ProductNode implements Serializable{
    private static final long serialVersionUID = -7060210544600464481L;   

	private String id;
	private List<Entry> entries;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	List<Entry> getEntries() {
		return entries;
	}
	void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
	
}
