package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedReplyPageDTO;
import com.tabeyo.domain.TimeFeedReplyVO;
import com.tabeyo.mapper.TimeFeedReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TimeFeedReplyServiceImpl implements TimeFeedReplyService {

	@Setter(onMethod_ = @Autowired)
	private TimeFeedReplyMapper timeFeedReplyMapper;
	
	@Override
	public TimeFeedReplyPageDTO getListPage(Criteria cri, Long fdNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TimeFeedReplyVO> getList(Criteria cri, Long fdNo) {
		log.info("get reply List of a Feed"+fdNo);
		
		return timeFeedReplyMapper.getList(cri, fdNo);
	}

	@Override
	public int modify(TimeFeedReplyVO fdRpNo) {
		log.info("remove ... "+fdRpNo);

		return timeFeedReplyMapper.update(fdRpNo);
	}

	@Override
	public int remove(Long fdRpNo) {
		log.info("modify..."+fdRpNo );

		return timeFeedReplyMapper.delete(fdRpNo);
	}

	@Override
	public TimeFeedReplyVO get(Long fdRpNo) {
		log.info("get.... "+fdRpNo);
		
		return timeFeedReplyMapper.read(fdRpNo);
	}

	@Override
	public int register(TimeFeedReplyVO fdRpNo) {
		log.info("register ..."+fdRpNo);
		
		return timeFeedReplyMapper.insert(fdRpNo);
	}

}
