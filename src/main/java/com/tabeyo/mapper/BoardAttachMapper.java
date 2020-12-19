package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.BoardAttachVO;

public interface BoardAttachMapper {
	public void deleteAll(Long bno);
	
	public void insert(BoardAttachVO vo);
	public void delete(String uuid);
	public List<BoardAttachVO> findByBno(Long bno);
}
