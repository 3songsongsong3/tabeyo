package com.tabeyo.service;

import java.util.List;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReplyPageDTO;
import com.tabeyo.domain.ReplyVO;

public interface ReplyService {
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
	public List<ReplyVO> getList(Criteria cri, Long bno);
	public int modify(ReplyVO vo);
	public int remove(Long rno);
	public ReplyVO get(Long rno);
	public int register(ReplyVO vo);
}










