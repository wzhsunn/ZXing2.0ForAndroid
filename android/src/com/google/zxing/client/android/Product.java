package com.google.zxing.client.android;

import java.io.Serializable;

public final class Product implements Serializable{
	private int id;
	private String name;
	private String date;
	private String place;
	private String duty;
	public Product(int id, String name,  String duty, String date, String place) {
		this.setId(id);
		this.setName(name);
		this.setDuty(duty);
		this.setDate(date);
		this.setPlace(place);
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	

}
