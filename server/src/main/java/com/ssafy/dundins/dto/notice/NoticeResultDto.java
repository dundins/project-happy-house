package com.ssafy.dundins.dto.notice;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NoticeResultDto {
	private int result; // success
	private NoticeDto dto; // 상세 
	private List<NoticeDto> list; // 목록
	private int count; // 글 개수
}
