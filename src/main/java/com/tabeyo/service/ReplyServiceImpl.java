package com.tabeyo.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReplyPageDTO;
import com.tabeyo.domain.ReplyVO;
import com.tabeyo.mapper.BoardMapper;
import com.tabeyo.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("ReplyServiceImpl register() vo : " + vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1);		//댓글 수 1 증가
		return mapper.insert(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("ReplyServiceImpl remove() rno : " + rno);
		boardMapper.updateReplyCnt(rno, -1);			//댓글 수 1 감소
		return mapper.delete(rno);
	}
	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(mapper.getListWithPaging(cri, bno), 
							    mapper.getCountByBno(bno));
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("ReplyServiceImpl getList() bno : " + bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("ReplyServiceImpl modify() vo : " + vo);
		return mapper.update(vo);
	}


	@Override
	public ReplyVO get(Long rno) {
		log.info("ReplyServiceImpl get() rno : " + rno);
		return mapper.read(rno);
	}
}
