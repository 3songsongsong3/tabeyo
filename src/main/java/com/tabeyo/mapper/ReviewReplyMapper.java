package com.tabeyo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewReplyVO;

public interface ReviewReplyMapper {
	public int getCountByRvNo(Long rvNo);	//리뷰댓글수 조회
	public List<ReviewReplyVO> 				//리뷰댓글 목록
	getListWithPaging(
		@Param("cri") Criteria cri,
		@Param("rvNo") Long rvNo
			);
	public int insert(ReviewReplyVO vo);	//리뷰댓글 작성
	public int update(ReviewReplyVO vo);	//리뷰댓글 수정
	public int delete(Long rvRno);			//리뷰댓글 삭제


	
}
