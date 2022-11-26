package com.ssafy.util;

public class News {

	private int newsNo;
	private String img;
	private String title;
	private String url;
	private String content;
	private String company;
	private String regTime;

	
	public int getNewsNo() {
		return newsNo;
	}

	public void setNewsNo(int newsNo) {
		this.newsNo = newsNo;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCreatedDate() {
		return regTime;
	}

	public void setCreatedDate(String createdDate) {
		this.regTime = createdDate;
	}

	@Override
	public String toString() {
		return "News [img=" + img + ", title=" + title + ", url=" + url + ", content=" + content + ", company="
				+ company + ", createdDate=" + regTime + "]";
	}
}
