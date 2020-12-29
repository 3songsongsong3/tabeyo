package com.tabeyo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedReplyPageDTO;
import com.tabeyo.domain.TimeFeedReplyVO;



public interface TimeFeedReplyMapper {
	
	
	
	public List<TimeFeedReplyVO> getListWithPaging(@Param("cri") Criteria cri,@Param("fdNo") Long fdNo);
	public int getCountByFdno(Long fdNo);
	public List<TimeFeedReplyVO> getList(@Param("cri") Criteria cri,@Param("fdNo") Long fdNo);
	public int update(TimeFeedReplyVO fdRpNo);
	public int delete(Long fdRpNo);
	public TimeFeedReplyVO read(Long fdRpNo);
	public int insert(TimeFeedReplyVO fdRpNo);
}
