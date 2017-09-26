package cn.waynechu.topblog.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLUtil {
	
	
	/**
	 * 提供网络编码的方法
	 * @param msg  编码的参数
	 * @param charset 编码字符集
	 * @return
	 */
	public static String getEncoder(String msg,String charset){
		try {
			return URLEncoder.encode(msg,charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String  getEncoder(String msg){
			return getEncoder(msg, "UTF-8");
	}
	
	
	
	
}
