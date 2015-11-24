package com.walter.csdn.bean;

public class NewsItem {
	private int id;
	private String title;
	private String link;
	private String date;
	private String imgLink;
	private String content;
	private int newsType;

	public int getNewsType() {
		return this.newsType;
	}

	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImgLink() {
		return this.imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return "NewsItem [id=" + this.id + ", title=" + this.title + ", date="
				+ this.date + ", newsType=" + this.newsType + "]";
	}
}
