package com.test;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@SpringBootApplication
@Slf4j
public class WebTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTestApplication.class, args);
    }


    @RequestMapping("/test/{msg}")
    public String test(@PathVariable String msg){
        return "test " + msg;
    }


    @RequestMapping("/upload")
    public String test(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D:/Test/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return "上传失败！";
    }
}