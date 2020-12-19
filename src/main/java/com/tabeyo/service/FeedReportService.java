package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.FeedReportVO;

public interface FeedReportService {
	public int getTotalCount(Criteria cri);		//전체 신고 수 가져오기
	public void register(FeedReportVO board);		// 신고하기
	public FeedReportVO get(Long bno);				//신고 조회 
//	public List<BoardVO> getList();				
	public List<FeedReportVO> getList(Criteria cri);	//신고 전체 조회 - 페이
}
