package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.ReviewAttachVO;

public interface ReviewAttachMapper {
	
	public void insert(ReviewAttachVO vo);
	public void delete(String uuid);
	public List<ReviewAttachVO> findByRvNo(Long rvNo);
	public void deleteAll(Long rvNo);
	public List<ReviewAttachVO> getOldFiles();

}
