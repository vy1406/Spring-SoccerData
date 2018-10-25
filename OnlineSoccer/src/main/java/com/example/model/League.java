package com.example.model;

public class League {
	private String id;
	private String country_id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "LeagueDao [id=" + id + ", country_id=" + country_id + ", name=" + name + "]";
	}
	
	
}
