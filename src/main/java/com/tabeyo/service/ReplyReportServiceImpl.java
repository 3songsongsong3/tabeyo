package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReplyReportVO;
import com.tabeyo.mapper.ReplyReportMapper;

import lombok.Setter;

@Service
public class ReplyReportServiceImpl implements ReplyReportService {
	@Setter(onMethod_ = @Autowired)
	private ReplyReportMapper replyReportMapper;
	
	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void register(ReplyReportVO board) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReplyReportVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReplyReportVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

}
