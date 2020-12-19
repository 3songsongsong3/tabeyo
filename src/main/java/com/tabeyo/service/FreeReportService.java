package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.FreeReportVO;

public interface FreeReportService {
	public int getTotalCount(Criteria cri);		//전체 게시물 수 가져오기
	public void register(FreeReportVO board);		//게시물 추가
	public FreeReportVO get(Long bno);				//게시물 조회
//	public List<BoardVO> getList();				//게시물 전체 조회
	public List<FreeReportVO> getList(Criteria cri);	//게시물 전체 조회 - 페이

}
