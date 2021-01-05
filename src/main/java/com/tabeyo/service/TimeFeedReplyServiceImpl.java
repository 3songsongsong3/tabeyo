package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedReplyPageDTO;
import com.tabeyo.domain.TimeFeedReplyVO;
import com.tabeyo.domain.TimeFeedVO;
import com.tabeyo.mapper.TimeFeedMapper;
import com.tabeyo.mapper.TimeFeedReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TimeFeedReplyServiceImpl implements TimeFeedReplyService {

	@Setter(onMethod_ = @Autowired)
	private TimeFeedReplyMapper timeFeedReplyMapper;
	
	// Transactional 처리를 위해 TimeFeedMapper 주입  20201230 
	@Setter(onMethod_ = @Autowired)
	private TimeFeedMapper timeFeedMapper;
	
	
	@Override
	public TimeFeedReplyPageDTO getListPage(Criteria cri, Long fdNo) {


		return new TimeFeedReplyPageDTO(
				timeFeedReplyMapper.getListWithPaging(cri, fdNo),
				timeFeedReplyMapper.getCountByFdno(fdNo));
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
	public TimeFeedReplyVO get(Long fdRpNo) {
		log.info("get.... "+fdRpNo);
		
		return timeFeedReplyMapper.read(fdRpNo);
	}

	// 20201230 Transactional 추가
	@Transactional
	@Override
	public int register(TimeFeedReplyVO fdRpNo) {
		log.info("register ..."+fdRpNo);
		
		timeFeedMapper.updateReplyCnt(fdRpNo.getFdNo(), 1);
		
		return timeFeedReplyMapper.insert(fdRpNo);
	}
	// 20201230 Transactional 추가
		@Transactional
		@Override
		public int remove(Long fdRpNo) {
			log.info("modify..."+fdRpNo );

			// 게시물 번호를 알아야 함 . 
			
			TimeFeedReplyVO vo = timeFeedReplyMapper.read(fdRpNo);
			timeFeedMapper.updateReplyCnt(vo.getFdNo(), -1);
			
			
			return timeFeedReplyMapper.delete(fdRpNo);
		}
}
