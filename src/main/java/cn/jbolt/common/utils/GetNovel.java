package cn.jbolt.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.jbolt.common.entity.Book;
import cn.jbolt.common.entity.SearchResult;

public class GetNovel {
	
//	private static final List<Book> books = new ArrayList<Book>();
	public final static String SEARCH_PATH="https://sou.xanbhx.com/search?siteid=qula&q=";
	
	/**
     * 获取搜索结果
     * @param keywords
     * @return
     * @throws IOException
     */
    public static List<SearchResult> getNovelUrl(String keywords) throws IOException {
 
        String url = SEARCH_PATH + keywords.trim();
        Document document = Jsoup.connect(url).get();
       
        List<SearchResult> list = new ArrayList<SearchResult>();
        for (Element element : document.select("ul li")) {
        	SearchResult result = new SearchResult();
        	result.setBookType(element.select(".s1").text());
        	result.setBookName(element.select(".s2 > a").text());
        	result.setUrl(element.select(".s2 > a").attr("abs:href"));
        	result.setAuthor(element.select(".s4").text());
        	result.setSearchKey(keywords);
        	result.setLastChapter(element.select(".s3 >a").text());
        	result.setLastUrl(element.select(".s3 > a").attr("abs:href"));
        	result.setLastDate(element.select(".s6").text());
        	list.add(result);
		}
        list.remove(0);
        return list;
    }
    
    /**
     * 获取目录
     * @param url
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> getNovelIndex(String url) throws IOException {
        List<Map<String, String>> list = new ArrayList<>();
        Document document = Jsoup.connect(url).get();
        String bookName = document.select("h1").text();
        String lastTime = document.select("div#info").select("p").eq(2).toString();
        String lastPage = document.select("div#info").select("p").eq(3).select("a").text();
        String lastOneUrl = document.select("div#info").select("p").eq(3).select("a").attr("abs:href");
        String introduce = document.select("div#intro").text();
        for (Element a : document.getElementsByTag("a")) {
            if (a.hasAttr("style") && "".equals(a.attr("style")) && a.attr("href").contains("/book/")) {
                Map<String, String> m = new HashMap<>();
                m.put("url", a.attr("abs:href"));
                m.put("name", a.text());
                m.put("bookName", bookName);
                m.put("author", document.select("h1").next().text());
                m.put("lastOneUrl", lastOneUrl);
                m.put("introduce", introduce);
                m.put("lastTime", lastTime);
                m.put("lastPage", lastPage);
                list.add(m);
            }
        }
        return list;
    }
    

    /**
     * 获取章节内容
     * @param url
     * @return
     * @throws IOException
     */
    public static Map<String, Object> getText(String url) throws IOException {
       
    	Document document = Jsoup.connect(url).get();
        String title = document.select("title").text();
        
        String lastUrl =document.select("#A1").attr("abs:href").toString();
        String nextUrl =document.select("#A3").attr("abs:href").toString();
        String bookName = document.select("div.con_top").select("a").eq(1).text();
        Element content = document.getElementById("content");
        
//        String text = content.html().replaceAll("&nbsp;", "")
//				 .replaceAll("<br />", "\r\n")
//				 .replaceAll("亲~本站域名:\"166小说\"的简写谐音166xs.com，很好记哦！www.166xs.com好看的小说强烈推荐：", "")
//				 .replaceAll("166小说阅读网", " ")
//				 ;
        String text = content.text();
        Map<String, Object> map =  new HashMap<String, Object>();
        
        map.put("title", title);
        map.put("text", text);
        map.put("nowUrl", url);
        map.put("lastUrl", lastUrl);
        map.put("nextUrl", nextUrl);
        map.put("listUrl", url.substring(0, url.lastIndexOf("/")+1));
        map.put("bookName", bookName);
        return map;
    }
    
    //最近的小说
    public  static List<Book> getNewNovel() {
    	
    	Elements elements =getDoc("https://www.qu.la/").select("div#newscontent").select("ul > li");
    	List<Book> books = new ArrayList<Book>();
    	int num =1;
    	for (Element element : elements) {
    		Book book = new Book();
    		book.setBookType(element.select("span.s1").text());
    		book.setName(element.select("span.s2").text());
    		book.setUrl(element.select("span.s2 > a").attr("abs:href"));
    		book.setLastOneUrl(element.select("span.s3 > a").attr("abs:href"));
    		book.setLastPage(element.select("span.s3 > a").text());
    		book.setAuthor(element.select("span.s4").text());
    		book.setLastTime(element.select("span.s5").text());
    		books.add(book);
    		
    		if(num == 30) {
        		break;
        	}
        	num++;
		}
    	
    	return books;
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
}
