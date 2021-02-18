package com.techelevator.model;

import java.util.List;

public class Venue {
	
	private int id;
	private String name;
	private String cityName;
	private String stateName;
	private List<String> categories;
	private String description;
	private List<Space> spaces;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Space> getSpaces() {
		return spaces;
	}
	public void setSpaces(List<Space> spaces) {
		this.spaces = spaces;
	}
	
	

}
