package com.pactera.taobaoprogect.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 访问网络获取类
 * */
public class HttpUtils {

	public static InputStream getStreamFromURL(String imageURL) {
		InputStream in=null;
		try {
			URL url=new URL(imageURL);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			in=connection.getInputStream();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
		
	}
}
