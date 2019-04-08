package cn.jbolt.index;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;

import cn.jbolt.common.entity.SearchResult;
import cn.jbolt.common.utils.GetNovel;
/**
 * IndexController 指向系统访问首页
 * @author jbolt.cn
 * @email 909854136@qq.com
 * @date 2018年11月4日 下午9:02:52
 */
public class IndexController extends Controller {
	/**
	 * 首页Action
	 */
	public void index() {
		render("index.html");
	}
	
	public void search() {
		
		List<SearchResult> lists = null;
	        try {
	        	lists = GetNovel.getNovelUrl(getPara("name"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		renderJson(lists);
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