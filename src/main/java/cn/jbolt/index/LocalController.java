package cn.jbolt.index;

import com.jfinal.core.Controller;

import cn.jbolt.common.utils.GetLocalFile;

public class LocalController extends Controller{

	public void index() {
		setAttr("list", GetLocalFile.getAllFile());
		render("list.html");
	}
	
	public void read() {
		setAttr("content", GetLocalFile.getOneBook(getPara("path").toString()));
		render("content.html");
	}
}
