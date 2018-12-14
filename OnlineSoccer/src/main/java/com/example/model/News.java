package com.example.model;
// ---------------------------------
// class will represent a single piece of news. that will be shown on the index.html
//---------------------------------

public class News {
	private String title;
	private String datePublished;
	private String description;
	private String url;
	private NewsImage newsImage;
		
	public NewsImage getNewsImage() {
		return newsImage;
	}
	public void setNewsImage(NewsImage newsImage) {
		this.newsImage = newsImage;
	}
	@Override
	public String toString() {
		return "News [title=" + title + ", datePublished=" + datePublished + ", description=" + description + ", url="
				+ url + ", newsImage=" + newsImage + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
