package com.ssafy.dundins.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.dundins.dto.BookMarkParamDto;
import com.ssafy.dundins.dto.HouseInfoDto;

@Mapper
public interface BookMarkDao {
	public List<HouseInfoDto> getBookmarkHouseDetailListById(String userId);
//	public List<HouseOnGoingDto> getBookmarkHouseOngoingListById(String userId);
	
	public int insertBookmarkHouseDetail(BookMarkParamDto bookMarkParamDto);
	public int insertBookmarkHouseOngoing(BookMarkParamDto bookMarkParamDto);
	
	public int deleteBookmarkHouseDetail(BookMarkParamDto bookMarkParamDto);
	public int deleteBookmarkHouseOngoing(BookMarkParamDto bookMarkParamDto);
}
