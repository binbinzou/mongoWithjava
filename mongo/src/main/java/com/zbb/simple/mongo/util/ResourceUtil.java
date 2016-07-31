package com.zbb.simple.mongo.util;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zbb 读取资源文件属性
 */
public class ResourceUtil {

	private static Map map = new ConcurrentHashMap();

	public static void loadResource(String fileName){
		ResourceBundle bundle = ResourceBundle.getBundle(fileName);
		map.put(fileName, bundle);
	}
	
	public static String getPorperty(String fileName,String key){
		ResourceBundle bundle = (ResourceBundle) map.get(fileName);
		try {
			if(bundle!=null){
				return bundle.getString(key);
			}else{
				loadResource(fileName);
				return getPorperty(fileName, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
