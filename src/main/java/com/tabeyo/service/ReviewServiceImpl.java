package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewAttachVO;
import com.tabeyo.domain.ReviewVO;
import com.tabeyo.mapper.ReviewAttachMapper;
import com.tabeyo.mapper.ReviewMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReviewServiceImpl implements ReviewService {

	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;

	@Setter(onMethod_ = @Autowired)
	private ReviewAttachMapper reviewAttachMapper;

	// 리뷰첨부파일목록
	@Override
	public List<ReviewAttachVO> getAttachList(Long rvNo) {
		log.info("get Attach list by rvNo " + rvNo);
		return reviewAttachMapper.findByRvNo(rvNo);
	}

	// 점포리뷰수
	@Override
	public int getTotalCount(Criteria cri) {
		log.info("ReviewServiceImpl...getTotalCount()");
		return reviewMapper.getTotalCount(cri);
	}

	// 리뷰목록 with Paging
	@Override
	public List<ReviewVO> getList(Criteria cri) {
		log.info("ReviewServiceImpl...getList() with criteria : " + cri);
		return reviewMapper.getListWithPaging(cri);
	}

	// 리뷰조회
	@Override
	public ReviewVO get(Long rvNo, Long businNo) {
		log.info("ReviewServiceImpl...get()");
		return reviewMapper.read(rvNo, businNo);
	}

	// 리뷰작성
	@Transactional
	@Override
	public void register(ReviewVO Review) {
		reviewMapper.insert(Review);

		if (Review.getReviewAttachList() == null || Review.getReviewAttachList().size() <= 0) {
			return;
		}

		// 첨부 파일이 있으면
		Review.getReviewAttachList().forEach(reivewAttach -> {
			reivewAttach.setRvNo(Review.getRvNo());
			reviewAttachMapper.insert(reivewAttach);

		});
	}

	// 리뷰수정
	@Transactional
	@Override
	public boolean modify(ReviewVO Review) {
		log.info("ReviewServiceImpl...modify()");
		reviewAttachMapper.deleteAll(Review.getRvNo());// 기존 첨부파일 삭제 보드에서 보드넘버를 꺼내서

		boolean modifyResult = reviewMapper.update(Review) == 1;// 게시물 수정

		// 게시물 수정에 성공하고 첨부파일 목록이 있으면
		if (modifyResult && Review.getReviewAttachList() != null && Review.getReviewAttachList().size() > 0) {
			Review.getReviewAttachList().forEach(attach -> {
				attach.setRvNo(Review.getRvNo());
				reviewAttachMapper.insert(attach);// 첨부파일 등록
			});
		}

		return modifyResult;
	}

	// 리뷰삭제
	@Override
	public boolean remove(Long rvNo, Long businNo) {
		log.info("ReviewServiceImpl...remove()");

		reviewAttachMapper.deleteAll(rvNo);

		return reviewMapper.delete(rvNo, businNo) == 1;
	}

}