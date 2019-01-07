package com.thinkit.microservicecloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class SDKdownloadController {

    private Logger logger  = LoggerFactory.getLogger(SDKdownloadController.class);

    @Value("${sdk.offline_asr_doc}")
    private String offline_asr_doc;


    @RequestMapping(value="/provider/sdk/offlineasrSDK/{session}", method = RequestMethod.GET)
    ResponseEntity<InputStreamResource> download_offlineasrSDK(HttpServletRequest request){

        String filepath = offline_asr_doc;

        logger.info("filepath: "+filepath);
        FileSystemResource file = new FileSystemResource(filepath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        try {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
