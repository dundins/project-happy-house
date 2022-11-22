package com.ssafy.dundins.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.util.Crawling;
import com.ssafy.util.News;

@RestController
@RequestMapping("/news")
public class NewsController {

	Crawling craw = new Crawling();
	
	public static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@GetMapping("/")
	public ResponseEntity<List<News>> getNews() throws Exception {
		logger.info("news - 뉴스 호출");
		return new ResponseEntity<List<News>>(craw.getNews(), HttpStatus.OK);
	}
}
