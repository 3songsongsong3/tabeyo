package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.ReviewReportVO;
import com.tabeyo.mapper.ReviewReportMapper;

import lombok.Setter;

@Service
public class ReviewReportServiceImpl implements ReviewReportService{
	@Setter(onMethod_ = @Autowired)
	private ReviewReportMapper reviewReportMapper;
	
	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void register(ReviewReportVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReviewReportVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReviewReportVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

}
