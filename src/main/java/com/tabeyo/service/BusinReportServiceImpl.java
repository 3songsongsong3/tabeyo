package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.BusinReportVO;
import com.tabeyo.domain.Criteria;
import com.tabeyo.mapper.BusinReportMapper;

import lombok.Setter;

@Service
public class BusinReportServiceImpl implements BusinReportService{
	
	@Setter(onMethod_ = @Autowired)
	private BusinReportMapper businReportMapper;
	
	
	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void register(BusinReportVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BusinReportVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinReportVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

}
