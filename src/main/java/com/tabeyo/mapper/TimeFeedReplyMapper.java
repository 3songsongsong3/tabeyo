package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedReplyPageDTO;
import com.tabeyo.domain.TimeFeedReplyVO;



public interface TimeFeedReplyMapper {
	public TimeFeedReplyPageDTO getListPage(Criteria cri, Long fdRpNo);
	public List<TimeFeedReplyVO> getList(Criteria cri, Long fdRpNo);
	public int modify(TimeFeedReplyVO vo);
	public int remove(Long fdRpNo);
	public TimeFeedReplyVO get(Long fdRpNo);
	public int register(TimeFeedReplyVO vo);
}
