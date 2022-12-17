package com.ssafy.dundins.service;

import java.util.List;

import com.ssafy.dundins.dto.BookMarkHouseDto;
import com.ssafy.dundins.dto.BookMarkResultDto;

public interface BookMarkService {
	public List<BookMarkResultDto> getBookmarkHouseDetailListById(String userId) throws Exception;
	
	public int insertBookmarkHouseDetail(BookMarkHouseDto bookMarkHouseDto) throws Exception;
	
	public int deleteBookmarkHouseDetail(BookMarkHouseDto bookMarkHouseDto) throws Exception;
}
