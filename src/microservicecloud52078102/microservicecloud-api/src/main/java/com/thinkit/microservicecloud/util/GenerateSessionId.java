package com.thinkit.microservicecloud.util;



public class GenerateSessionId {

	public static String getSid(){
		
		String uuid = java.util.UUID.randomUUID().toString();

		
		//long num = System.currentTimeMillis();
		
		//String sid= uuid+"_"+num;
		
		return uuid.replace("-", "");
	}
	

}
