package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.TimeFeedAttachVO;


public interface TimeFeedAttachMapper {
	public List<TimeFeedAttachVO> getOldFiles();
	
	public void deleteAll(Long fdNo);
	public void insert(TimeFeedAttachVO vo);
	public void delete(String uuid);
	public List<TimeFeedAttachVO> findByBno(Long fdNo);
}
