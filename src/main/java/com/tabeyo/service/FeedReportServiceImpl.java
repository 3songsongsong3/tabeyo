package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.FeedReportVO;
import com.tabeyo.mapper.FeedReportMapper;

import lombok.Setter;

@Service
public class FeedReportServiceImpl implements FeedReportService {
	@Setter(onMethod_ = @Autowired)
	private FeedReportMapper feedReportMapper;
	
	
	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void register(FeedReportVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FeedReportVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeedReportVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

}
