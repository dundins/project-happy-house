package com.ssafy.dundins.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookMarkResultDto {
	private String aptCode;
	private String aptName;
	private String address;
	private String buildYear;
	private String lat;
	private String lng;
}
