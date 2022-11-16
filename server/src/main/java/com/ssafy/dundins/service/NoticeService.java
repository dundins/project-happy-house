package com.ssafy.dundins.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.dundins.dto.notice.NoticeDto;
import com.ssafy.dundins.dto.notice.NoticeParamDto;
import com.ssafy.dundins.dto.notice.NoticeResultDto;

public interface NoticeService {
	public NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto);
	public NoticeResultDto noticeDelete(int noticeId);
	public NoticeResultDto noticeUpdate(NoticeDto dto, MultipartHttpServletRequest request);
	public NoticeResultDto noticeInsert(NoticeDto dto, MultipartHttpServletRequest request);
	public NoticeResultDto noticeList(NoticeParamDto noticeParamDto);
	public NoticeResultDto noticeLatestList(NoticeParamDto noticeParamDto);
	public NoticeResultDto noticeListSearchWord(NoticeParamDto noticeParamDto);
}
