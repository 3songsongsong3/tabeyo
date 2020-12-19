package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.FeedRpReportVO;
import com.tabeyo.mapper.FeedReportMapper;
import com.tabeyo.mapper.FeedRpReportMapper;

import lombok.Setter;

@Service
public class FeedRpReportServiceImpl implements FeedRpReportService {

	@Setter(onMethod_ = @Autowired)
	private FeedRpReportMapper feedRpReportMapper;
	
	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void register(FeedRpReportVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FeedRpReportVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeedRpReportVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

}
