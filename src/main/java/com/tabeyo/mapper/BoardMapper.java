package com.tabeyo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tabeyo.domain.BoardVO;
import com.tabeyo.domain.Criteria;

public interface BoardMapper {
	public void updateReplyCnt(@Param("bno") Long bno,
							   @Param("amount") int amount);
	
	//전체 게시물 수 가져오기
	public int getTotalCount(Criteria cri);
	
	//전체 게시물 가져오기 - 페이징 구현
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//게시물 수정
	public int update(BoardVO board);
	
	//게시물 하나 삭제
	public int delete(Long bno);
	
	//게시물 하나 조회
	public BoardVO read(Long bno);

//	@Select("SELECT * FROM tbl_board WHERE bno > 0")
	public List<BoardVO> getList();
	
	//insert만 수행하는 경우
	public void insert(BoardVO board);
	
	//insert가 수행되고 생성된 PK를 알아야 하는 경우
	public void insertSelectKey(BoardVO board);
	
}









