package com.example.serviceImpl;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.model.News;
import com.example.service.NewsService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class NewsServiceImpl implements NewsService {

	@Override
	public ArrayList<News> getNewsByTopic(String topic) throws UnirestException {
		
		String url = "https://contextualwebsearch-news-search-v1.p.mashape.com/api/Search/NewsSearchAPI?autocorrect=true&count=5&q=Football+";
		url += topic;
		HttpResponse<JsonNode> response = Unirest.get(url)
				.header("X-Mashape-Key", "sdpA5mNyFHmshPaz2GricZFb2fMep1MfVlxjsn9z1gehpzfRJ3")
				.header("Accept", "application/json").asJson();

		try {
		
			return parseToNews(response.getBody().getObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// ----------------------------
	// returns an array of news to show at the index.html
	// ----------------------------
	private ArrayList<News> parseToNews(JSONObject json) throws JSONException {
		ArrayList<News> news = new ArrayList<>();
		News current;
		JSONArray keys = json.names();
		JSONArray values = (JSONArray) json.opt("value");
		
		for ( int i = 0 ; i < values.length() ; i ++ ) {
			JSONObject curJsonObject = values.getJSONObject(i);			
			current = createNewsFromJSONObject(curJsonObject);
			news.add(current);
		}
		
		return news;
	}
	
	// ----------------------------
	// for readability
	// ----------------------------
	private News createNewsFromJSONObject(JSONObject argJSONObject) throws JSONException {
		News news = new News();
		String description = argJSONObject.getString("description");
		String title = argJSONObject.getString("title");
		String url = argJSONObject.getString("url");
		String datePublished = argJSONObject.getString("datePublished");
		
		news.setUrl(url);
		news.setDatePublished(datePublished);
		news.setTitle(title);
		news.setDescription(description);
		return news;
	}
	
	@Override
	public String getRandomNews() {
		// TODO Auto-generated method stub
		return null;
	}

}
