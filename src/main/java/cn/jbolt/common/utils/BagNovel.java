package cn.jbolt.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import cn.jbolt.common.entity.Book;
import cn.jbolt.common.entity.SearchResult;

public class BagNovel {

	public final static String SEARCH_PATH="http://www.shubaoqu.com/modules/article/search.php?searchkey=%CE%D2%D5%E6%CA%C7&searchtype=articlename";
	
	public static List<Book> searchBag(String word,String page) {
		String s ="";
		try {
			 s = URLEncoder.encode(word, "gbk");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String url = "http://www.shubaoqu.com/modules/article/search.php?"
				+ "searchkey="+s+"&searchtype=articlename&page="+page;
		
		Elements elements = getDoc(url).select("#nr");
		List<Book> lists = new ArrayList<Book>();
		
		String lastPage = getDoc(url).select("#pagestats").text();
		
		for (Element element : elements) {
			Book book = new Book();
			book.setName(element.select(".odd").text());
			book.setUrl(element.select(".odd > a").attr("abs:href"));
			book.setLastPage(lastPage.substring(lastPage.length()-1));
//			book.setNowUrl("/bag/search?searchkey="+word+"&page=");
			book.setTitle(word);
			lists.add(book);
		}
		
		return lists;
	}
	
	
	
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
 public static Book getText(String url){
        
    	Document document = getDoc(url);
        String title = document.select("h1").text();
        
        String lastUrl =document.select(".bottem2 > a").eq(1).attr("abs:href").toString();
        String listUrl =document.select(".bottem2 > a").eq(2).attr("abs:href").toString();
        String nextUrl =document.select(".bottem2 > a").eq(3).attr("abs:href").toString();
        String text = document.select("#content").html()
//        						.replaceAll("&nbsp;", "	")
//        						.replaceAll("<br>", " ")
//        						.replaceAll("<br />", "\r\n")
        						;
        Book book = new Book();
        book.setTitle(title);
        book.setText(text);
        book.setLastUrl(lastUrl);
        book.setListUrl(listUrl);
        book.setNextUrl(nextUrl);
        return book;
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
