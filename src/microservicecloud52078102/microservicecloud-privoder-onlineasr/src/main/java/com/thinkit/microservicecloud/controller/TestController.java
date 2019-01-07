package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.onlineasr.PointCut;
import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class TestController{


	Logger logger = LoggerFactory.getLogger(TestController.class);
/*@RequestMapping("/getCeshi")
	public @ResponseBody Map<String,Object> getCeshi(
			@RequestParam(value="data") String data,
			@RequestParam(value="steData") String steData,
			@RequestParam(value="startIndex") String startIndex,
			@RequestParam(value="endIndex") String endIndex) throws InterruptedException, ExecutionException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ExecutorService exec = Executors.newCachedThreadPool();//工头
		// 这个是多线程的方式 处理 上传的数据、并且在最后一次结束的时候、将base64拼接起来、生成.wav文件
		// 并且返回文件的存储地址。
		Future<Object> future= exec.submit(new ThreadBase64(steData, data, startIndex,endIndex));
		return (Map<String, Object>) future.get();
}*/


	@RequestMapping(value = "/onlineasr/recording", method = RequestMethod.POST)
	public Map<String,Object> rec(@RequestBody PointCut info, HttpServletResponse response) throws  Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");


		ExecutorService exec = Executors.newCachedThreadPool();//工头
		// 这个是多线程的方式 处理 上传的数据、并且在最后一次结束的时候、将base64拼接起来、生成.wav文件
		// 并且返回文件的存储地址。
		Future<Object> future= exec.submit(new ThreadBase64(info.getSteData(), info.getData(),info.getStartIndex(),info.getEndIndex()));
		return (Map<String, Object>) future.get();
	}
}