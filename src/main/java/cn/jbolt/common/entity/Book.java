package cn.jbolt.common.entity;

import java.util.List;

public class Book {

	private String name;
	private String author;
	private String path;
	private String url;
	private String title;
	private String text;
	private String nowUrl;
	
	private String lastUrl;
	
	private String nextUrl;
	private String listUrl;
	private String lastTime;
	
	private String lastPage;
	private String lastOneUrl;
	//简介
	private String introduce;
	
	private String bookType;
	
	private List<SearchResult> listUrls;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getNowUrl() {
		return nowUrl;
	}
	public void setNowUrl(String nowUrl) {
		this.nowUrl = nowUrl;
	}
	public String getLastUrl() {
		return lastUrl;
	}
	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}
	public String getNextUrl() {
		return nextUrl;
	}
	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}
	public String getListUrl() {
		return listUrl;
	}
	public void setListUrl(String listUrl) {
		this.listUrl = listUrl;
	}
	public List<SearchResult> getListUrls() {
		return listUrls;
	}
	public void setListUrls(List<SearchResult> listUrls) {
		this.listUrls = listUrls;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastPage() {
		return lastPage;
	}
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getLastOneUrl() {
		return lastOneUrl;
	}
	public void setLastOneUrl(String lastOneUrl) {
		this.lastOneUrl = lastOneUrl;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	
	
}
