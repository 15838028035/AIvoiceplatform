package com.thinkit.microservicecloud.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import sun.misc.BASE64Decoder;
/**
 * 多线程 处理
 * @author aodun
 *
 */
public class ThreadBase64 implements  Callable<Object>{
	private String id;
	private String base64;
	private String index;
	private String endindex;
	
	public ThreadBase64(String id, String base64,String index, String endindex){
		this.id = id;
		this.base64 = base64;
		this.index = index;
		this.endindex = endindex;
	}

	public Object call() throws Exception {
		Map<String,Object> whereMap = new HashMap<String, Object>();
		String code = "1001";
		String path = "";
		try {
			if(Integer.valueOf(this.index).intValue() == 1){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("base64",new StringBuffer(this.base64)); // 转写内容
				map.put("startIndex",this.index); // 起始
				map.put("endIndex",this.endindex); // 结束
				// 创建一个
				Bse64MapInfo.setWhere(this.id, map);
			}else{
				Object o = Bse64MapInfo.getWhere(this.id);
				if(o!= null){
					Map<String,Object> map = (Map<String, Object>) o;
					StringBuffer buffer = (StringBuffer) map.get("base64");
					buffer.append(this.base64);
					map.put("basse64", buffer);
					map.put("startIndex", this.index);
				}	
			}
			if(Integer.valueOf(this.index).intValue() == Integer.valueOf(this.endindex).intValue()){
				String filePath = "/mnt/thinkit/openplatform/openplatform/privoder-onlineasr/audiowav/";
				//String filePath = "D:/Voice/open/";
				String fileName = UUID.randomUUID().toString()+".wav";
				boolean is = base64Wav(this.id,fileName,filePath);
				if(is){
					Bse64MapInfo.delWhere(this.id);;
					code = "1003";
					path = filePath+fileName;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = "1002";
		}
		whereMap.put("path", path);
		whereMap.put("code", code);
		return whereMap;
	}
	
	public boolean base64Wav(String steData,String fileName,String filePath){
		Object o = Bse64MapInfo.getWhere(steData);
		Map<String,Object> map = (Map<String, Object>) o;
		String in = map.get("base64").toString();
		System.out.println(in.length());
		boolean is = isFile(in, filePath, fileName);
		if(is)

			return true;
		
		return false;
	}
	
	public boolean isFile(String base64,String filePath,String fileName){
		 BASE64Decoder decoder = new BASE64Decoder(); 
		 try {
			byte[] b = decoder.decodeBuffer(base64.split("data:audio/wav;base64,")[1]);
			for(int i=0;i<b.length;++i)  
           {  
               if(b[i]<0)  
               {	//调整异常数据  
                   b[i]+=256;  
               }  
           }
			String filePaName = filePath+fileName;
			OutputStream outputStream = new FileOutputStream(filePaName);
			outputStream.write(b);  
			outputStream.flush();  
			outputStream.close();  
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}  
		return true;
	}
	
	
}
