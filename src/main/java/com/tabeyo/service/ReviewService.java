package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewAttachVO;
import com.tabeyo.domain.ReviewVO;

public interface ReviewService {
	
	public List<ReviewAttachVO> getAttachList(Long bno);	//리뷰첨부파일목록 가져옴
	//view -> Controller -> Service -> Persistance -> myBatis -> DB
	public int 				getTotalCount(Criteria cri);	//점포리뷰 숫자가져오기
	public List<ReviewVO> 	getList(Criteria cri);			//리뷰 목록
	public ReviewVO 		get(Long rvNo, 					//리뷰 조회
			Long businNo);	
	public void 			register(ReviewVO review);		//리뷰 작성
	public boolean 			modify(ReviewVO review);		//리뷰 수정
	public boolean 			remove(Long rvNo, 				//리뷰 삭제
							Long businNo);		
		
	

	
}
