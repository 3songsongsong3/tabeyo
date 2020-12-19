package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewReplyPageDTO;
import com.tabeyo.domain.ReviewReplyVO;

public interface ReviewReplyService {
	// 리뷰댓글목록페이징
	public ReviewReplyPageDTO getListPage(Criteria cri, Long rvNo);

	// 리뷰댓글목록
	public List<ReviewReplyVO> getList(Criteria cri, Long rvNo);

	// 리뷰댓글등록
	public int register(ReviewReplyVO vo);

	// 리뷰댓글수정
	public int modify(ReviewReplyVO vo);

	// 리뷰댓글삭제
	public int remove(Long rvRno);

}
