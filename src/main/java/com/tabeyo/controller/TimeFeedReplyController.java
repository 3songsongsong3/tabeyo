package com.tabeyo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.TimeFeedReplyPageDTO;
import com.tabeyo.domain.TimeFeedReplyVO;
import com.tabeyo.service.TimeFeedReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/tabeFR/")
@Log4j
@AllArgsConstructor
public class TimeFeedReplyController {
	private TimeFeedReplyService timeFeedReplyService;
	
	// 댓글 수정 
	@RequestMapping(value="/{fdRpNo}", 
	method={ RequestMethod.PUT, RequestMethod.PATCH },
	consumes="application/json",
	produces={ MediaType.TEXT_PLAIN_VALUE } )
	public ResponseEntity<String> modify(
							 @RequestBody TimeFeedReplyVO vo, 
							 @PathVariable("fdRpNo") Long fdRpNo){
		
	log.info("ReplyController modify : " + vo);
	log.info("ReplyController rvRno : " + fdRpNo);
	vo.setFdRpNo(fdRpNo);
	int modifyCount =  timeFeedReplyService.modify(vo);	//댓글 수정
	log.info("REPLY MODIFY COUNT : " + modifyCount);
	
	//성공이면 200, 실패면 500 반환
	return modifyCount == 1
	? new ResponseEntity<>("success", HttpStatus.OK)
	: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	// 댓글 조회 
	@GetMapping(value="/{fdRpNo}" , 
				produces= { MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<TimeFeedReplyVO> get(@PathVariable("fdRpNo") Long fdRpNo) {
		
	log.info("ReplyController get : " + fdRpNo);
	
	return new ResponseEntity<>(timeFeedReplyService.get(fdRpNo), HttpStatus.OK);
	} 
	
	
	
	//댓글 삭제 
	@DeleteMapping(value="/{fdRpNo}" , 
			produces= { MediaType.TEXT_PLAIN_VALUE})
	//@PreAuthorize("principal.username == #vo.userId")	//댓글 작성자 확인
	public ResponseEntity<String> remove(@PathVariable("fdRpNo") Long fdRpNo) {
		
	log.info("ReplyController remove : " + fdRpNo);
	
	return timeFeedReplyService.remove(fdRpNo) == 1
	   ? new ResponseEntity<>("success", HttpStatus.OK)
	   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	// 댓글 목록 출력 
	@GetMapping(value = "/pages/{fdNo}/{page}",
				produces ={ MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
	//public ResponseEntity<List<ReplyVO>> getList(
	public ResponseEntity<List<TimeFeedReplyVO>> getList(
				@PathVariable("page") int page,
				@PathVariable("fdNo") Long fdNo ) {
		
	log.info("ReplyController getList : ");
	Criteria cri = new Criteria(page, 10);
	log.info("ReplyController cri : " + cri);
	//return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
	return new ResponseEntity<>(timeFeedReplyService.getList(cri, fdNo), HttpStatus.OK);
	}
	
	
	
	
	
	// 댓글 등록
	@PostMapping(value="/register",
	 consumes="application/json",
	 produces= { MediaType.TEXT_PLAIN_VALUE } )
//	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> create(@RequestBody TimeFeedReplyVO vo){
		
	log.info("ReviewReplyController create vo : " + vo);
	
	int insertCount =  timeFeedReplyService.register(vo);	//
	
	log.info("REPLY INSERT COUNT : " + insertCount);
	
	//성공이면 200, 실패면 500 반환
	return insertCount == 1
	? new ResponseEntity<>("success", HttpStatus.OK)
	: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
