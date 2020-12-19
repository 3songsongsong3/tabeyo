package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tabeyo.domain.BoardAttachVO;
import com.tabeyo.domain.BoardVO;
import com.tabeyo.domain.Criteria;
import com.tabeyo.mapper.BoardAttachMapper;
import com.tabeyo.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
//@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;

	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		log.info("BoardServiceImpl...modify()");
		
		attachMapper.deleteAll(board.getBno());				//기존 첨부파일 삭제
		boolean modifyResult = mapper.update(board) == 1;	//게시물 수정
		
		if(modifyResult == true //게시물 수정에 성공하고, 첨부파일 목록이 있으면 
		   && board.getAttachList() != null 
		   && board.getAttachList().size() > 0)	{ 
				board.getAttachList().forEach(attach -> {
					attach.setBno(board.getBno());  
					attachMapper.insert(attach); 	//첨부파일 등록
				});
		}
		return modifyResult;
	}
	
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		return attachMapper.findByBno(bno);
	}

	@Transactional
	@Override
 	public void register(BoardVO board) {
		log.info("register... : " + board);
//		mapper.insert(board);
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null 
		   || board.getAttachList().size() <= 0) { //첨부 파일이 없으면
			return;
		}
		
		//첨부 파일이 있으면
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
		
	}

	@Override
	public int getTotalCount(Criteria cri) {
		log.info("BoardServiceImpl...getTotalCount()");
		return mapper.getTotalCount(cri);
	}

	@Override
	public boolean remove(Long bno) {
		log.info("BoardServiceImpl...remove() bno : " + bno);
		attachMapper.deleteAll(bno);	//첨부파일 삭제
		return mapper.delete(bno) == 1;	//게시물 삭제
	}
	
	@Override
	public BoardVO get(Long bno) {
		log.info("BoardServiceImpl...get()");
		return mapper.read(bno);
	}
	
	@Override
//	public List<BoardVO> getList() {
//		log.info("BoardServiceImpl...getList()");
//		return mapper.getList();
//	}
	public List<BoardVO> getList(Criteria cri) {
		log.info("BoardServiceImpl...getList() with criteria : " + cri);
		return mapper.getListWithPaging(cri);
	}
}







