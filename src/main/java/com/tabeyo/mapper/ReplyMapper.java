package com.tabeyo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tabeyo.domain.BoardVO;
import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReplyVO;

public interface ReplyMapper {
	public int getCountByBno(Long bno);
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri,
										   @Param("bno") Long bno);
	public int update(ReplyVO vo);
	public int delete(Long rno);
	public ReplyVO read(Long rno);
	public int insert(ReplyVO vo);
}









