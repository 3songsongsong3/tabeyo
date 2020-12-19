package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.UserVO;

public interface UserMapper {

	// id찾기
	public UserVO idFind(String email);

	// pw찾기
	public UserVO pwFind(String email);
	
	// 회원가입
	public void insert(UserVO user);

	// 소비자 마이페이지
	public void myPage();

	// 사업자 마이페이지
	public void businMyPage();

	// 회원정보조회
	public UserVO read(String userid);

	// 회원정보 수정
	public int update(UserVO user);

	// 회원 탈퇴
	public int delete(String userId);

	// 관리자 마이페이지
	public void adminMyPage();

	// 전체회원관리 - 페이징구현
	public List<UserVO> getUserListWithPaging(Criteria cri);

	// 회원 포인트수정(관리자권한)
	public int pointUpdate(String userId);

	// 회원 강제탈퇴 (관리자권한)
	public int forceDelete(String userId);





}
