package com.neo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    protected final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
    @RequestMapping("/")
    public String index() {
        logger.info("返回：[{}]","中华人民共和国万岁");
        return "Hello Spring Boot 2.0，中华人民共和国万岁!";
    }
}