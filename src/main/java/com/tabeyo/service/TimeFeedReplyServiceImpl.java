package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedReplyPageDTO;
import com.tabeyo.domain.TimeFeedReplyVO;
import com.tabeyo.mapper.TimeFeedReplyMapper;

import lombok.Setter;

@Service
public class TimeFeedReplyServiceImpl implements TimeFeedReplyService {

	@Setter(onMethod_ = @Autowired)
	private TimeFeedReplyMapper timeFeedReplyMapper;
	
	@Override
	public TimeFeedReplyPageDTO getListPage(Criteria cri, Long fdNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TimeFeedReplyVO> getList(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(TimeFeedReplyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Long fdRpNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TimeFeedReplyVO get(Long fdRpNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int register(TimeFeedReplyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
