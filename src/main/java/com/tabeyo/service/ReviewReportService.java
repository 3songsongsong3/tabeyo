package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewReportVO;

public interface ReviewReportService {
	public int getTotalCount(Criteria cri);		//전체 게시물 수 가져오기
	public void register(ReviewReportVO board);		//게시물 추가
	public ReviewReportVO get(Long bno);				//게시물 조회
//	public List<BoardVO> getList();				//게시물 전체 조회
	public List<ReviewReportVO> getList(Criteria cri);	//게시물 전체 조회 - 페이

}
