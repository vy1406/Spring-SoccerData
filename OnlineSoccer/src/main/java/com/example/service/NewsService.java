package com.example.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface NewsService {

	public HttpResponse<JsonNode> getNewsByTopic() throws UnirestException;
	public String getRandomNews();
}
