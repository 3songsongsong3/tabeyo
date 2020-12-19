package com.tabeyo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewVO;

public interface ReviewMapper {

	// 점포리뷰가져오기 - 페이징구현
	public List<ReviewVO> getListWithPaging(Criteria cri);

	// 리뷰 조회
	public ReviewVO read(Long rvNo, Long businNo);

	// 리뷰 수정
	public int update(ReviewVO review);

	// 리뷰 삭제
	public int delete(Long rvNo, Long businNo);

	// 리뷰 작성
	public void insert(ReviewVO review);

	// 점포 리뷰 수 가져오기
	public int getTotalCount(Criteria cri);

	// 리뷰 댓글 수 카운트
	public void updateReplyCnt(@Param("rvNo") Long rvNo, @Param("amount") int amount);

}
