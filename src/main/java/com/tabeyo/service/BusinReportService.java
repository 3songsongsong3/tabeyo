package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.BusinReportVO;
import com.tabeyo.domain.Criteria;

public interface BusinReportService {
	public int getTotalCount(Criteria cri);		//전체 게시물 수 가져오기
	public void register(BusinReportVO board);		//게시물 추가
	public BusinReportVO get(Long bno);				//게시물 조회
//	public List<BoardVO> getList();				//게시물 전체 조회
	public List<BusinReportVO> getList(Criteria cri);	//게시물 전체 조회 - 페이징
}
