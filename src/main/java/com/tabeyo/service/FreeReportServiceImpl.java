package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.FreeReportVO;
import com.tabeyo.mapper.FeedRpReportMapper;
import com.tabeyo.mapper.FreeReportMapper;

import lombok.Setter;

@Service
public class FreeReportServiceImpl  implements FreeReportService  {
	@Setter(onMethod_ = @Autowired)
	private FreeReportMapper freeReportMapper;
	
	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void register(FreeReportVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FreeReportVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeReportVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

}
