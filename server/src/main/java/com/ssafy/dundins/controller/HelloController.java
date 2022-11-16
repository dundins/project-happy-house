package com.ssafy.dundins.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api("테스트용 컨트롤러")
@RequestMapping("/test")
@RestController
public class HelloController {

	@GetMapping(value = "/")
	public ResponseEntity<String> noticeList() {
		return new ResponseEntity<String>("Hello!", HttpStatus.OK);
	}
}
