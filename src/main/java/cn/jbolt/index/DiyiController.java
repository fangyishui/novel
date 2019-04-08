package cn.jbolt.index;

import com.jfinal.core.Controller;

import cn.jbolt.common.entity.Book;
import cn.jbolt.common.utils.DiyiNovel;

public class DiyiController extends Controller{

	
	public void index() {
		
		setAttr("list", DiyiNovel.getJingPin("http://www.shubaoqu.com/jingpinxiaoshuo/"));
		
		render("index.html");
	}
	
	public void search() {
//		BagNovel.searchBag("一念");
		
//		renderText(DiyiNovel.searchBag(getPara("name")));
	}
	
	//查看一本书列表
	public void bagList() {
		Book book =	DiyiNovel.getNovelList(getPara("url"));
		setAttr("list", book.getListUrls());
		setAttr("BookName", book.getName());
		setAttr("author", book.getAuthor());
		setAttr("lastTime", book.getLastTime());
		
		render("list.html");
	}
	
	
	//查看一页正文
	public void firstText() {
//		Book book =null;
//		try {
//		  book =DiyiNovel.getText("http://38.103.161.143/forum/thread-10400262-1-5.html");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		setAttr("book", book);
//		render("content.html");
		renderText(DiyiNovel.getText("http://38.103.161.143/forum/thread-9129986-1-8.html"));
	}
}
