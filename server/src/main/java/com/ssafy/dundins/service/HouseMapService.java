package com.ssafy.dundins.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.dundins.dto.HouseInfoDto;
import com.ssafy.dundins.dto.HouseSaleDto;
import com.ssafy.dundins.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptInSearch(Map<String, String> map) throws SQLException;
	
	void insertHouseSales(HouseSaleDto houseSaleDto) throws SQLException;
	List<HouseSaleDto> getAllHouseSales() throws SQLException;
	HouseSaleDto getHouseSale(int saleId) throws SQLException;
	void deleteHouseSale(int saleId) throws SQLException;
	
	List<Integer> getDealAmountAvg(String aptCode) throws SQLException;
}
