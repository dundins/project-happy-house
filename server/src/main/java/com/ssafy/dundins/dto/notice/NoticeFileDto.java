package com.ssafy.dundins.dto.notice;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 파일 업로드 
@ToString
@Getter
@Setter
public class NoticeFileDto {
	private int fileId;
	private int noticeId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private String fileUrl;
	private LocalDateTime regDt;
}