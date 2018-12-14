package com.example.model;

public class Team {

	private String id;
	private int team_api_id;
	private String name;
	private String short_name;
	
	public int getTeam_api_id() {
		return team_api_id;
	}
	public void setTeam_api_id(int team_api_id) {
		this.team_api_id = team_api_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", team_api_id=" + team_api_id + ", name=" + name + ", short_name=" + short_name
				+ "]";
	}

	
	
}
