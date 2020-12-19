package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.ReviewReplyReportVO;


public interface ReviewReplyReportMapper {
		//신고 기능 
		public void insert(ReviewReplyReportVO rp);
		
		//신고 하나 조회
		public ReviewReplyReportVO read(Long rptNo);
		
		// 신고  전체 조회
		// @Select("SELECT * FROM tbl_board WHERE bno > 0")
		public List<ReviewReplyReportVO> getList();
}
