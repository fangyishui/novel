package cn.jbolt.index;

import java.util.List;

import com.jfinal.core.Controller;

import cn.jbolt.common.entity.Book;
import cn.jbolt.common.utils.BagNovel;

public class BagController extends Controller{

	public void index() {
		render("index.html");
	}
	
	public void search() {
		List<Book> lists = BagNovel.searchBag(getPara("name"),getPara("page"));
		renderJson(lists);
	}
	
	public void search2() {
		List<Book> lists = BagNovel.searchBag(getPara("name"),getPara("page"));
		setAttr("list", lists);
		render("page.html");
	}
	
	//查看一本书列表
	public void bagList() {
		Book book =	BagNovel.getNovelList(getPara("url"));
		setAttr("list", book.getListUrls());
		setAttr("BookName", book.getName());
		setAttr("author", book.getAuthor());
		setAttr("lastTime", book.getLastTime());
		
		render("list.html");
	}
	
	
	//查看一页正文
	public void bagText() {
		
		Book  book =BagNovel.getText(getPara("url"));
		setAttr("book", book);
		render("content.html");
	}
}
