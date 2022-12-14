package com.ssafy.dundins.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dundins.dto.HouseInfoDto;
import com.ssafy.dundins.dto.HouseSaleDto;
import com.ssafy.dundins.dto.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptInSearch(Map<String, String> map) throws SQLException;
	
	void insertHouseSales(HouseSaleDto houseSaleDto) throws SQLException;
	List<HouseSaleDto> getAllHouseSales() throws SQLException;
	HouseSaleDto getHouseSale(int saleId) throws SQLException;
	void deleteHouseSale(int saleId) throws SQLException;
	
	List<Integer> getDealAmountAvg(String aptCode) throws SQLException;
}
