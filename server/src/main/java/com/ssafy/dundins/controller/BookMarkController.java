package com.ssafy.dundins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dundins.dto.BookMarkResultDto;
import com.ssafy.dundins.service.BookMarkService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/bookmark")
@Api("북마크 컨트롤러  API V1")
public class BookMarkController {

	
	@Autowired
	private BookMarkService bookMarkService;
	
	
	@GetMapping("/house/{userId}")
	public ResponseEntity<List<BookMarkResultDto>> idCheck(@PathVariable("userId") String userId) throws Exception {
		try {
			System.out.println(userId);
			List<BookMarkResultDto> res = bookMarkService.getBookmarkHouseDetailListById(userId);
			return new ResponseEntity<List<BookMarkResultDto>>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<BookMarkResultDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
