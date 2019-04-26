package cn.jbolt.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.jbolt.common.entity.Book;

public class GetLocalFile {
//	public final static String FILE_PATH = "C:\\领导";

	public final static String FILE_PATH = "/home/JSOUP";

	public static List<Book> getAllFile(){
		
		List<Book> lists = new ArrayList<Book>();
		
		File file = new File(FILE_PATH);
		// 如果这个路径是文件夹
		if (file.isDirectory()) {
		// 获取路径下的所有文件
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				Book book = new Book();
				book.setPath(files[i].getPath());
				book.setName(files[i].getName());
				lists.add(book);
			}
		} else {
			System.out.println("文件：" + file.getPath());
		}
		
		return lists;
	}
	
	public static String getOneBook(String path) {
		
		String lineTxt = null;
		StringBuffer sb = new StringBuffer();
		try { 
	        String encoding="UTF-8"; 
	        File file=new File(path); 
	        if(file.isFile() && file.exists()){ //判断文件是否存在 
	        	
	          InputStreamReader read = new InputStreamReader( 
	          new FileInputStream(file),encoding);//考虑到编码格式 
	          BufferedReader bufferedReader = new BufferedReader(read); 
	          
	          while((lineTxt = bufferedReader.readLine()) != null){ 
	        	  sb.append(lineTxt);
	          } 
	          read.close(); 
	    }else{ 
	      System.out.println("找不到指定的文件"); 
	    } 
	    } catch (Exception e) { 
	      System.out.println("读取文件内容出错"); 
	      e.printStackTrace(); 
	    } 
		
		return sb.toString();
	}
}
