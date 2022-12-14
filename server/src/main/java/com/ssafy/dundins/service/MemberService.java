package com.ssafy.dundins.service;

import java.sql.SQLException;

import com.ssafy.dundins.dto.MemberDto;

public interface MemberService {

	public void registMember(MemberDto memberDto) throws Exception;
	int idCheck(String userId) throws SQLException;
	void updateMember(MemberDto memberDto) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
}
