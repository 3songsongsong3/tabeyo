package com.tabeyo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedVO;
import com.tabeyo.domain.UserVO;

public interface TimeFeedMapper {
		public void updateReplyCnt(@Param("fdNo") Long fdNo,
			   @Param("amount") int amount);

		//전체 게시물 수 가져오기
		public int getTotalCount(Criteria cri);
		
		//전체 게시물 가져오기 - 페이징 구현
		public List<TimeFeedVO> getListWithPaging(Criteria cri);
		
		//게시물 수정
		public int update(TimeFeedVO fdNo);
		
		//게시물 하나 삭제
		public int delete(TimeFeedVO fdNo);
		
		//게시물 하나 조회
		public TimeFeedVO feed(Long fdNo);
		
		//@Select("SELECT * FROM tbl_board WHERE bno > 0")
		public List<TimeFeedVO> getList(String userId);
		
		//insert만 수행하는 경우
		public void insert(TimeFeedVO board);
		
		//insert가 수행되고 생성된 PK를 알아야 하는 경우
		public void insertSelectKey(TimeFeedVO board);
		
		//2020.12.10 타베모노가타리 추가 
		public UserVO viewMember(String userId);

		public UserVO getUserId(String userId);
		
		// 좋아요 기능
		public void likeIt(TimeFeedVO board);
		
		// 좋아요 개수 추가 
		public int likeCount(TimeFeedVO board) ;
		
}
