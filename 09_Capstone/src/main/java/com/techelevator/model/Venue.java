package com.techelevator.model;


public class Venue {
	
	private long id;
	private String name;
	private int city_id;
	private String city_name;
	private String state_abbreviation;
	private String description;
	private String category_name;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getState_abbreviation() {
		return state_abbreviation;
	}
	public void setState_abbreviation(String state_abbreviation) {
		this.state_abbreviation = state_abbreviation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	
	
	

}
