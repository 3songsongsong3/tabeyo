package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewReplyReportVO;
import com.tabeyo.mapper.ReviewReplyReportMapper;

import lombok.Setter;

@Service
public class ReviewRpReportServiceImpl implements ReviewRpReportService{
	@Setter(onMethod_ = @Autowired)
	private ReviewReplyReportMapper reviewReplyReportMapper;
	
	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void register(ReviewReplyReportVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReviewReplyReportVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReviewReplyReportVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

}
