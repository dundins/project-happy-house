package com.ssafy.dundins.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dundins.dto.BookMarkHouseDto;
import com.ssafy.dundins.dto.BookMarkResultDto;

@Mapper
public interface BookMarkMapper {
	public List<BookMarkResultDto> getBookmarkHouseDetailListById(String userId) throws SQLException;
//	public List<HouseOnGoingDto> getBookmarkHouseOngoingListById(String userId);
	
	public int insertBookmarkHouseDetail(BookMarkHouseDto bookMarkHouseDto) throws SQLException;
//	public int insertBookmarkHouseOngoing(BookMarkHouseDto bookMarkParamDto);
	
	public int deleteBookmarkHouseDetail(BookMarkHouseDto bookMarkHouseDto) throws SQLException;
//	public int deleteBookmarkHouseOngoing(BookMarkHouseDto bookMarkParamDto);
}
