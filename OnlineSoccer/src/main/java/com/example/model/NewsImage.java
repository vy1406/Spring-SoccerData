package com.example.model;

// -----------------------------
// part of News class. 
// NewsImage represents the image that will be shown on the news' block in index.html
//-----------------------------
public class NewsImage {

	private String thumbnail;
	private int width;
	private int height;
	private String url;
	private int thumbnailWidth;
	private int thumbnailHeight;
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getThumbnailWidth() {
		return thumbnailWidth;
	}
	public void setThumbnailWidth(int thumbnailWidth) {
		this.thumbnailWidth = thumbnailWidth;
	}
	public int getThumbnailHeight() {
		return thumbnailHeight;
	}
	public void setThumbnailHeight(int thumbnailHeight) {
		this.thumbnailHeight = thumbnailHeight;
	}
	@Override
	public String toString() {
		return "NewsImage [thumbnail=" + thumbnail + ", width=" + width + ", height=" + height + ", url=" + url
				+ ", thumbnailWidth=" + thumbnailWidth + ", thumbnailHeight=" + thumbnailHeight + "]";
	}
	
	
	 
	
}
