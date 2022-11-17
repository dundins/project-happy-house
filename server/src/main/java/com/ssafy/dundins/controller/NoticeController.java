package com.ssafy.dundins.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.dundins.dto.notice.NoticeDto;
import com.ssafy.dundins.dto.notice.NoticeOriginDto;
import com.ssafy.dundins.dto.notice.NoticeParamDto;
import com.ssafy.dundins.dto.notice.NoticeResultDto;
import com.ssafy.dundins.service.NoticeService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "http://localhost:9999", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
		RequestMethod.OPTIONS })
@RestController
@Api("게시판 컨트롤러  API V1")
public class NoticeController {

	@Autowired
	NoticeService service;

	public static final int SUCCESS = 1;

	@GetMapping(value = "/notices/all")
	public ResponseEntity<List<NoticeOriginDto>> getNotices() {
		return new ResponseEntity<List<NoticeOriginDto>>(service.getNotices(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/notices")
	public ResponseEntity<NoticeResultDto> noticeList(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto;
		System.out.println(noticeParamDto);
		if (noticeParamDto.getSearchWord().isEmpty()) {
			noticeResultDto = service.noticeList(noticeParamDto);
		} else {
			noticeResultDto = service.noticeListSearchWord(noticeParamDto); // 검색어가 포함되어 있을 때
		}

		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 최근 등록 매물 5개까지
	@GetMapping(value = "/notices/latest")
	public ResponseEntity<NoticeResultDto> noticeLatestList(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		System.out.println(noticeParamDto);

		noticeResultDto = service.noticeLatestList(noticeParamDto);

		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/notices/{noticeId}")
	public ResponseEntity<NoticeResultDto> noticeDetail(@PathVariable int noticeId, HttpSession session) {

		NoticeParamDto noticeParamDto = new NoticeParamDto();
		noticeParamDto.setNoticeId(noticeId);
//	    UserDto userDto = (UserDto) session.getAttribute("userDto");
//	    if (userDto != null) noticeParamDto.setUserSeq(userDto.getUserSeq());

		System.out.println("신호  : " + noticeId);
		NoticeResultDto noticeResultDto = service.noticeDetail(noticeParamDto);

		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/notices")
	public ResponseEntity<NoticeResultDto> noticeInsert(NoticeDto noticeDto, MultipartHttpServletRequest request) {

		HttpSession session = request.getSession();
//	    UserDto userDto = (UserDto) session.getAttribute("userDto");

//	    noticeDto.setUserSeq(userDto.getUserSeq());

		NoticeResultDto noticeResultDto = service.noticeInsert(noticeDto, request);

		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/notices/{noticeId}")
	public ResponseEntity<NoticeResultDto> noticeUpdate(NoticeDto noticeDto, MultipartHttpServletRequest request) {

		HttpSession session = request.getSession();
//	    UserDto userDto = (UserDto) session.getAttribute("userDto");
		System.out.println("noticeUpdate " + noticeDto);
//	    noticeDto.setUserSeq(userDto.getUserSeq());

		NoticeResultDto noticeResultDto = service.noticeUpdate(noticeDto, request);

		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(value = "/notices/{noticeId}")
	public ResponseEntity<NoticeResultDto> noticeDelete(@PathVariable(value = "noticeId") int noticeId) {
		NoticeResultDto noticeResultDto = service.noticeDelete(noticeId);

		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
