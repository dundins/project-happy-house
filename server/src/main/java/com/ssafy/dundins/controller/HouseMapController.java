package com.ssafy.dundins.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dundins.dto.BoardDto;
import com.ssafy.dundins.dto.BoardParameterDto;
import com.ssafy.dundins.dto.HouseInfoDto;
import com.ssafy.dundins.dto.HouseSaleDto;
import com.ssafy.dundins.dto.SidoGugunCodeDto;
import com.ssafy.dundins.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@ApiOperation(value = "아파트 목록", notes = "지역코드와 매매계약월을 기준으로 아파트 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/aptlist/{lawd_cd}/{deal_ymd}", produces = "application/json;charset=utf-8")
	public ResponseEntity<String> aptList(@PathVariable("lawd_cd") String lawdCd,
			@PathVariable("deal_ymd") String dealYmd) throws IOException {
		logger.info("sido - 호출");
		String serviceKey = "KK5i2B1iqTTjhyER70zMyDRXjsjtMwue3tOArkFinJeA%2B4%2F11q7k5CEKIOGxKmo0AkCqE5ccBjzNkId2pX5abQ%3D%3D";
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
																															 */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(lawdCd, "UTF-8")); /* 지역코드 */
		urlBuilder.append(
				"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYmd, "UTF-8")); /* 계약월 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		JSONObject json = XML.toJSONObject(sb.toString());
		String jsonStr = json.toString();

		return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}

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

	@GetMapping("/sale/{sale_id}")
	public ResponseEntity<HouseSaleDto> getHouseSale(@PathVariable("sale_id") int saleId) throws Exception {
		System.out.println("getHouseSale : " + saleId);
		return new ResponseEntity<HouseSaleDto>(haHouseMapService.getHouseSale(saleId), HttpStatus.OK);
	}

	@GetMapping("/sale")
	public ResponseEntity<List<HouseSaleDto>> getAllHouseSale() throws Exception {
		logger.info("getAllHouseSale - 호출");
		return new ResponseEntity<List<HouseSaleDto>>(haHouseMapService.getAllHouseSales(), HttpStatus.OK);
	}

	@PostMapping("/sale")
	public ResponseEntity<Void> inserHouseSale(@RequestBody HouseSaleDto houseSaleDto) throws Exception {
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

	@DeleteMapping("/sale/{sale_id}")
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
