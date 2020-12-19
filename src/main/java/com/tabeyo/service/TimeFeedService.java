 package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedAttachVO;
import com.tabeyo.domain.TimeFeedVO;
import com.tabeyo.domain.UserVO;



public interface TimeFeedService {
	public List<TimeFeedAttachVO> getAttachList(Long bno); 	//첨부파일 목록 가져오기
	public int getTotalCount(Criteria cri);		//전체 게시물 수 가져오기
	public void register(TimeFeedVO board);		//게시물 추가
	public boolean modify(TimeFeedVO fdNo);		//게시물 수정
	public boolean remove(TimeFeedVO fdNo);			//게시물 삭제
	public TimeFeedVO feed(Long fdNo);				//게시물 조회
//	public List<BoardVO> getList();				//게시물 전체 조회
	public List<TimeFeedVO> getList(String userId);	//게시물 전체 조회 
	public UserVO viewMember(String userId ) ;//2020.12.10 타베모노가타리 추가 
	
	
	public UserVO getUserId(String userId); // id값 url 파라미터로 받기 위한 메소드

	
}
