package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewReplyPageDTO;
import com.tabeyo.domain.ReviewReplyVO;
import com.tabeyo.mapper.ReviewMapper;
import com.tabeyo.mapper.ReviewReplyMapper;

import lombok.Setter;

@Service
public class ReviewReplyServiceImpl implements ReviewReplyService {
	@Setter(onMethod_ = @Autowired)
	private ReviewReplyMapper reviewReplyMapper;

	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;

	// 리뷰댓글목록페이징
	@Override
	public ReviewReplyPageDTO getListPage(Criteria cri, Long rvNo) {
		// TODO Auto-generated method stub
		return new ReviewReplyPageDTO(
				reviewReplyMapper.getListWithPaging(cri, rvNo),
				reviewReplyMapper.getCountByRvNo(rvNo));
	}

	// 리뷰댓글목록
	@Override
	public List<ReviewReplyVO> getList(Criteria cri, Long rvNo) {
		// TODO Auto-generated method stub
		return reviewReplyMapper.getListWithPaging(cri, rvNo);
	}

	// 리뷰댓글등록
	@Override
	public int register(ReviewReplyVO vo) {
		// TODO Auto-generated method stub
		return reviewReplyMapper.insert(vo);
	}

	// 리뷰댓글 수정
	@Override
	public int modify(ReviewReplyVO vo) {
		// TODO Auto-generated method stub
		return reviewReplyMapper.update(vo);
	}

	// 리뷰댓글삭제
	@Override
	public int remove(Long rvRno) {
		// TODO Auto-generated method stub
		return reviewReplyMapper.delete(rvRno);
	}

}