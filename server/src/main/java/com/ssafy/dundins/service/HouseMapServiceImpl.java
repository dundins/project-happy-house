package com.ssafy.dundins.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dundins.dao.HouseMapMapper;
import com.ssafy.dundins.dto.HouseInfoDto;
import com.ssafy.dundins.dto.HouseSaleDto;
import com.ssafy.dundins.dto.SidoGugunCodeDto;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getAptInSearch(Map<String, String> map) throws SQLException {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInSearch(map);
	}

	@Override
	public List<HouseSaleDto> getAllHouseSales() throws SQLException {
		return sqlSession.getMapper(HouseMapMapper.class).getAllHouseSales();
	}

	@Override
	public HouseSaleDto getHouseSale(int saleId) throws SQLException {
		return sqlSession.getMapper(HouseMapMapper.class).getHouseSale(saleId);
	}

	@Override
	public void deleteHouseSale(int saleId) throws SQLException {
		sqlSession.getMapper(HouseMapMapper.class).deleteHouseSale(saleId);
	}

	@Override
	public void insertHouseSales(HouseSaleDto houseSaleDto) throws SQLException {
		sqlSession.getMapper(HouseMapMapper.class).insertHouseSales(houseSaleDto);
		
	}
}
