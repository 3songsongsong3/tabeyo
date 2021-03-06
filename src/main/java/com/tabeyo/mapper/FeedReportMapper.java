package com.tabeyo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.FeedReportVO;


public interface FeedReportMapper {
	
		public void updateReplyCnt(@Param("rptNo") Long rptNo,
			   @Param("amount") int amount);
	
		//전체 신고 수 가져오기
		public int getTotalCount(Criteria cri);
		
		//전체 게시물 가져오기 - 페이징 구현
		public List<FeedReportVO> getListWithPaging(Criteria cri);

		//신고 기능 
		public void insert(FeedReportVO rp);
		
		//신고 하나 조회
		public FeedReportVO read(Long reportNo);
		
		// 신고  전체 조회
		// @Select("SELECT * FROM tbl_board WHERE bno > 0")
		public List<FeedReportVO> getList();
		
}
