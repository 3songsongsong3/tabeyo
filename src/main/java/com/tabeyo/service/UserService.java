package com.tabeyo.service;

import java.util.List;

import org.springframework.ui.Model;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.UserAttachVO;
import com.tabeyo.domain.UserVO;

public interface UserService {
	public List<UserAttachVO> getAttachList(String userId); // 첨부파일목록 가져옴
	// view -> Controller -> Service -> Persistance -> myBatis -> DB

	public void idFind(String email); // ID찾기

	public void pwFind(String userId, // PW찾기
			String email);

	public void register(UserVO user); // 회원가입

	public void read(String userId); // 회원정보조회

	public boolean modify(UserVO user); // 회원정보 수정

	public boolean remove(UserVO user); // 회원 탈퇴

	public List<UserVO> getList(Criteria cri); // 회원리스트

	public boolean pointModify(String userId, // 회원 포인트수정(관리자권한)
			Long point, Model model);

	public boolean forceRemove(String userId, // 회원 강제탈퇴(관리자권한)
			 Model model);
	
	


}
