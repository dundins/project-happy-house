package com.ssafy.dundins.dto.notice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class NoticeDto {
	private int noticeId;
	private int userSeq;
	private String userName;
	private String title;
	private String content;
	private int code; // 권한 분류 
	
	private LocalDateTime regDt;
	private int readCount;
	
	private boolean sameUser;
	
	private List<NoticeFileDto> fileList;


	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(
				regDt.toInstant(), ZoneId.systemDefault()
		);
	}
}