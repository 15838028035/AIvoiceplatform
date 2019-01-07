package com.thinkit.microservicecloud.controller;

import java.util.HashMap;
import java.util.Map;

public class Bse64MapInfo {
	
	private static final Map<String,Object> MAP_WHERE = new HashMap<String, Object>();
	
	public static Object getWhere(String key){
		return MAP_WHERE.get(key);
	}
	
	public static void setWhere(String key,Object value){
		MAP_WHERE.put(key, value);
	}
	
	public static void delWhere(String key){
		MAP_WHERE.remove(key);
	}
}
