package com.example.model;

import java.util.Date;

public class Match {

	private String id;
	private String country_id;
	private String league_id;
	private String season;
	private String stage;
	private String home_goal;
	private String away_goal;
	private int match_api_id;
	private int home_team_api_id;
	private int away_team_api_id;
	
	public int getMatch_api_id() {
		return match_api_id;
	}
	public void setMatch_api_id(int match_api_id) {
		this.match_api_id = match_api_id;
	}
	public int getHome_team_api_id() {
		return home_team_api_id;
	}
	public void setHome_team_api_id(int home_team_api_id) {
		this.home_team_api_id = home_team_api_id;
	}
	public int getAway_team_api_id() {
		return away_team_api_id;
	}
	public void setAway_team_api_id(int away_team_api_id) {
		this.away_team_api_id = away_team_api_id;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getHome_goal() {
		return home_goal;
	}
	public void setHome_goal(String home_goal) {
		this.home_goal = home_goal;
	}
	public String getAway_goal() {
		return away_goal;
	}
	public void setAway_goal(String away_goal) {
		this.away_goal = away_goal;
	}
	private Date date;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
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
	public String getLeague_id() {
		return league_id;
	}
	public void setLeague_id(String league_id) {
		this.league_id = league_id;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	@Override
	public String toString() {
		return "Match [id=" + id + ", country_id=" + country_id + ", league_id=" + league_id + ", season=" + season
				+ ", stage=" + stage + ", home_goal=" + home_goal + ", away_goal=" + away_goal + ", match_api_id="
				+ match_api_id + ", home_team_api_id=" + home_team_api_id + ", away_team_api_id=" + away_team_api_id
				+ ", date=" + date + "]";
	}
	

	
	
}
