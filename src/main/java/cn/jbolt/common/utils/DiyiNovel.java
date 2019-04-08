package cn.jbolt.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.jbolt.common.entity.Book;
import cn.jbolt.common.entity.SearchResult;

public class DiyiNovel {

	public static Document getDoc(String url) {
		
		Document doc =null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return doc;
	}
	
	
	 /**
     * 获取目录
     * @param url
     * @return
     * @throws IOException
     */
    public static Book getNovelList(String url){
        
        Document document = getDoc(url);
        
        String bookName = document.select("h1").text();
        
        String author = document.select("h1").next().text();
        
        String lastTime = document.select("#info").select("p").eq(2).text();
        
        Elements elements =  document.select("#list").select("a");
        
        List<SearchResult> strs = new ArrayList<SearchResult>();
        for (Element element : elements) {
        	SearchResult sr = new SearchResult();
        	sr.setUrl(element.attr("abs:href"));
        	sr.setName(element.text());
        	strs.add(sr);
		}
        
        Book book = new Book();
        book.setAuthor(author);
        book.setName(bookName);
        book.setListUrls(strs);
        book.setLastTime(lastTime);
        
        return book;
    }
	
	
	 /**
     * 获取章节内容
     * @param url
     * @return
     * @throws IOException
     */
    public static String getText(String url){
        
    	Document document = getDoc(url);
        String title = document.select("h1").text();
        
        String text = document.select("table").remove().select("div.t_msgfont.noSelect").text();
        
        Book book = new Book();
        book.setTitle(title);
        book.setText(text);
        return text;
    }
	
    
    public static List<Book> getJingPin(String url){
    	
    	List<Book> books = new ArrayList<Book>();
    	
    	Document doc = getDoc(url);
    	
    	Elements elements = doc.select("#main").select("a");
    	
    	for (Element element : elements) {
			Book book = new Book();
			book.setName(element.text());
    		book.setUrl(element.attr("abs:href"));
    		books.add(book);
		}
    	
    	return books;
    }
    
}
