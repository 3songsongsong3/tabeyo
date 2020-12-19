package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewReplyReportVO;

public interface ReviewRpReportService {
	public int getTotalCount(Criteria cri);		//전체 게시물 수 가져오기
	public void register(ReviewReplyReportVO board);		//게시물 추가
	public ReviewReplyReportVO get(Long bno);				//게시물 조회
//	public List<BoardVO> getList();				//게시물 전체 조회
	public List<ReviewReplyReportVO> getList(Criteria cri);	//게시물 전체 조회 - 페이
}
