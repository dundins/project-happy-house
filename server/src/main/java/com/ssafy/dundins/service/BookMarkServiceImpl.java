package com.ssafy.dundins.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dundins.dao.BookMarkMapper;
import com.ssafy.dundins.dto.BookMarkHouseDto;
import com.ssafy.dundins.dto.BookMarkResultDto;

@Service
public class BookMarkServiceImpl implements BookMarkService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BookMarkResultDto> getBookmarkHouseDetailListById(String userId) throws Exception {
		return sqlSession.getMapper(BookMarkMapper.class).getBookmarkHouseDetailListById(userId);
	}

	@Override
	public int insertBookmarkHouseDetail(BookMarkHouseDto bookMarkHouseDto) throws Exception {
		return sqlSession.getMapper(BookMarkMapper.class).insertBookmarkHouseDetail(bookMarkHouseDto);
	}

	@Override
	public int deleteBookmarkHouseDetail(BookMarkHouseDto bookMarkHouseDto) throws Exception {
		return sqlSession.getMapper(BookMarkMapper.class).deleteBookmarkHouseDetail(bookMarkHouseDto);
	}

}
