package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedReplyPageDTO;
import com.tabeyo.domain.TimeFeedReplyVO;


public interface TimeFeedReplyService {
	public TimeFeedReplyPageDTO getListPage(Criteria cri, Long fdNo);
	public List<TimeFeedReplyVO> getList(Criteria cri, Long fdNo);
	public int modify(TimeFeedReplyVO fdRpNo);
	public int remove(Long fdRpNo);
	public TimeFeedReplyVO get(Long fdRpNo);
	public int register(TimeFeedReplyVO fdRpNo);
}
