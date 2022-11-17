package com.ssafy.dundins.dto.notice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 글 목록을 가지고 올 때 서버쪽으로 보내는 데이터
@ToString
@Getter
@Setter
public class NoticeParamDto {

	private int limit;
	private int offset;
	private String searchWord;
	
	// 특정 게시글에 대한 상세가 들어가기 위해
	private int noticeId;
	private int userSeq;
}
