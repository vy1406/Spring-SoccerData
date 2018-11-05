package com.example.model;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component("dummyObject1")
public class dummyObject {
	private String name;
	private League[] leagues;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public League[] getLeagues() {
		return leagues;
	}
	public void setLeagues(League[] leagues) {
		this.leagues = leagues;
	}
	@Override
	public String toString() {
		return "dummyObject [name=" + name + ", leagues=" + Arrays.toString(leagues) + "]";
	}
	
	
	
}
