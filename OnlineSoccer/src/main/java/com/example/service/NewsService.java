package com.example.service;

import java.util.ArrayList;

import com.example.model.News;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface NewsService {

	public ArrayList<News> getNewsByTopic(String topic) throws UnirestException;
	public String getRandomNews();
}
