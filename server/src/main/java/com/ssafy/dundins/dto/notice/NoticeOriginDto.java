package com.ssafy.dundins.dto.notice;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeOriginDto {
	private int no;
	private int userNo;
	private String title;
	private String content;
	private LocalDateTime regtime;
	private int hit; 
}
