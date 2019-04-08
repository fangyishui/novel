package cn.jbolt.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import cn.jbolt.common.entity.SearchResult;

public class GetNovel {
	
	
	public final static String SEARCH_PATH="https://sou.xanbhx.com/search?siteid=qula&q=";
//	http://www.shubaoqu.com/
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
        	list.add(result);
		}
        
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
        for (Element a : document.getElementsByTag("a")) {
            if (a.hasAttr("style") && "".equals(a.attr("style")) && a.attr("href").contains("/book/")) {
                Map<String, String> m = new HashMap<>();
                m.put("url", a.attr("abs:href"));
                m.put("name", a.text());
                m.put("bookName", bookName);
                m.put("author", document.select("h1").next().text());
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
        Element content = document.getElementById("content");
        
        String text = content.html().replaceAll("&nbsp;", "")
				 .replaceAll("<br />", "\r\n")
				 .replaceAll("亲~本站域名:\"166小说\"的简写谐音166xs.com，很好记哦！www.166xs.com好看的小说强烈推荐：", "")
				 .replaceAll("166小说阅读网", " ")
				 ;
        Map<String, Object> map =  new HashMap<String, Object>();
        
        map.put("title", title);
        map.put("text", text);
        map.put("nowUrl", url);
        map.put("lastUrl", lastUrl);
        map.put("nextUrl", nextUrl);
        map.put("listUrl", url.substring(0, url.lastIndexOf("/")+1));
        return map;
    }
}
