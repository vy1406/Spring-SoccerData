package com.example.serviceImpl;

import com.example.service.NewsService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class NewsServiceImpl implements NewsService{

	@Override
	public HttpResponse<JsonNode> getNewsByTopic() throws UnirestException  {
		
		HttpResponse<JsonNode> response = Unirest.get("https://nuzzel-news-v1.p.mashape.com/news?count=11&q=chelsea")
				.header("X-Mashape-Key", "M4VX4ZfeQimsh7wXLc7tqbKRXnnNp1dxoamjsneu2x8MXOQ23E")
				.header("Accept", "application/json")
				.asJson();
		
		return response;
	}

	@Override
	public String getRandomNews() {
		// TODO Auto-generated method stub
		return null;
	}

}
