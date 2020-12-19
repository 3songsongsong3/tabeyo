package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.UserAttachVO;

public interface UserAttachMapper {
	
	public void insert(UserAttachVO vo);
	public void delete(String uuid);
	public List<UserAttachVO> findById(String userId);
	public void deleteAll(List<UserAttachVO> attachList);

}
