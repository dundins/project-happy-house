package com.ssafy.dundins.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dundins.dto.HouseInfoDto;
import com.ssafy.dundins.dto.HouseSaleDto;
import com.ssafy.dundins.service.HouseMapService;

@RestController
@RequestMapping("/sale")
public class SaleController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	
	@Autowired
	private HouseMapService haHouseMapService;
	
	@PostMapping("/search")
	public ResponseEntity<Map<String, Object>> searchApt(@RequestBody String code) throws Exception {
		String searchCode = code.replaceAll("\"", "");
		String keyCode = searchCode.substring(searchCode.length() - 1);
		System.out.println(keyCode);
		System.out.println(searchCode);
		Map<String, String> map = new HashMap<>();
		if (keyCode.equals("구"))
			map.put("key", "gugun");
		else if (keyCode.equals("동") || keyCode.equals("가"))
			map.put("key", "dong");
		else
			map.put("key", "apt");
//			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);

		map.put("code", searchCode);
		System.out.println(map);
		List<HouseInfoDto> infos = haHouseMapService.getAptInSearch(map);

		Map<String, Object> resultMap = new HashMap<>();
		if (keyCode.equals("구"))
			resultMap.put("type", "gugun");
		else
			resultMap.put("type", "dong");

		resultMap.put("data", infos);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@GetMapping("/{sale_id}")
	public ResponseEntity<HouseSaleDto> getHouseSale(@PathVariable("sale_id") int saleId) throws Exception {
		System.out.println("getHouseSale : " + saleId);
		return new ResponseEntity<HouseSaleDto>(haHouseMapService.getHouseSale(saleId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<HouseSaleDto>> getAllHouseSale() throws Exception {
		logger.info("getAllHouseSale - 호출");
		return new ResponseEntity<List<HouseSaleDto>>(haHouseMapService.getAllHouseSales(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> insertHouseSale(@RequestBody HouseSaleDto houseSaleDto) throws Exception {
		System.out.println("insert HouseSale - 호출");
		System.out.println(houseSaleDto);
		try {
			haHouseMapService.insertHouseSales(houseSaleDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{sale_id}")
	public ResponseEntity<Void> deleteHouseSale(@PathVariable("sale_id") int saleId) throws Exception {
		logger.info("deleteHouseSale - 호출");
		try {
			haHouseMapService.deleteHouseSale(saleId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/avg/{apt_code}")
	public ResponseEntity<List<Integer>> getDealAmontAvg(@PathVariable("apt_code") String aptCode) throws Exception {
		try {
			return new ResponseEntity<List<Integer>>(haHouseMapService.getDealAmountAvg(aptCode),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
