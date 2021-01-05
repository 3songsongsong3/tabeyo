package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedAttachVO;
import com.tabeyo.domain.TimeFeedVO;
import com.tabeyo.domain.UserVO;
import com.tabeyo.mapper.TimeFeedMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class TimeFeedServiceImpl implements TimeFeedService {

	@Setter(onMethod_ = @Autowired)
	private TimeFeedMapper timeFeedMapper;
	
	@Override
	public List<TimeFeedAttachVO> getAttachList(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 피드 추가
	@Override
	public void register(TimeFeedVO board) {
		// TODO Auto-generated method stub
		log.info("register... : " + board);

		timeFeedMapper.insertSelectKey(board);
		
		if(board.getAttachList() == null 
				   || board.getAttachList().size() <= 0) { //첨부 파일이 없으면
					return;
				}
				
				//첨부 파일이 있으면
			/*	board.getAttachList().forEach(attach -> {
					attach.setBno(board.getBno());
					attachMapper.insert(attach);
				});*/
				
	}

	@Override
	public boolean modify(TimeFeedVO fdNo) {
			log.info("BoardServiceImpl...modify()");
			
			boolean modifyResult = timeFeedMapper.update(fdNo) == 1;	//게시물 수정

			/*
			 *attachMapper.deleteAll(board.getBno()); //기존 첨부파일 삭제 
			  boolean modifyResult =mapper.update(board) == 1; //게시물 수정
			 * 
			 * if(modifyResult == true //게시물 수정에 성공하고, 첨부파일 목록이 있으면 && board.getAttachList()
			 * != null && board.getAttachList().size() > 0) {
			 * board.getAttachList().forEach(attach -> { attach.setBno(board.getBno());
			 * attachMapper.insert(attach); //첨부파일 등록 }); }
			 */
		return modifyResult;
	}

	@Override
	public boolean remove(TimeFeedVO fdNo) {
		

		return timeFeedMapper.delete(fdNo) == 1;
	}


		@Override
		public UserVO viewMember(String userId) {
				
			return timeFeedMapper.viewMember(userId);
		}

		@Override
		public List<TimeFeedVO> getList(String userId) {
			// TODO Auto-generated method stub
			return timeFeedMapper.getList(userId);
		}

		@Override
		public TimeFeedVO feed(Long fdNo) {
			log.info("TimeFeedServiceImpl...feed()");
			return timeFeedMapper.feed(fdNo);
		}

		@Override
		public UserVO getUserId(String userId) {
			return timeFeedMapper.getUserId(userId);
		}
}
