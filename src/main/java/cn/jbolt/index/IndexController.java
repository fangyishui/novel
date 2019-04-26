package cn.jbolt.index;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;

import cn.jbolt.common.entity.SearchResult;
import cn.jbolt.common.utils.GetNovel;
/**
 * IndexController 首页
 * 这是https://www.qu.la的爬虫
 */
public class IndexController extends Controller {
	/**
	 * 首页Action
	 */
	public void index() {
		setAttr("books", GetNovel.getNewNovel());
		render("index.html");
	}
	
	public void search() {
		List<SearchResult> lists = null;
	        try {
	        	lists = GetNovel.getNovelUrl(getPara("searchkey"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    setAttr("lists", lists);
		render("search.html");
	}
	
	public void reder() {
		Map<String, Object> map =null;
		try {
             map = GetNovel.getText(getPara("url").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
		setAttr("text", map.get("text"));
		setAttr("title", map.get("title"));
		setAttr("nowUrl", map.get("nowUrl"));
		setAttr("lastUrl", map.get("lastUrl"));
		setAttr("nextUrl", map.get("nextUrl"));
		setAttr("listUrl", map.get("listUrl"));
		setAttr("bookName", map.get("bookName"));
		render("content.html");
	}
	
	public void list() {
		List<Map<String, String>> map =null;
		try {
			map = GetNovel.getNovelIndex(getPara("url").toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		setAttr("list", map);
		render("list.html");
	}
}