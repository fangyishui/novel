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
	
	String s  ="<div class=\"pages\">\r\n" + 
			"		<div class=\"pagelink\" id=\"pagelink\">\r\n" + 
			"			<em id=\"pagestats\">1/6</em>\r\n" + 
			"			<a href=\"/modules/article/search.php?\r\n" + 
			"			searchkey=%B8%DF%CA%D6&searchtype=articlename&page=6\">6</a>\r\n" + 
			"		</div>\r\n" + 
			"		</div>";
	
	
	
}
