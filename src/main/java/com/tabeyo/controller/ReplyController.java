package com.tabeyo.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
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
import com.tabeyo.domain.ReplyPageDTO;
import com.tabeyo.domain.ReplyVO;
import com.tabeyo.domain.SampleVO;
import com.tabeyo.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies/")
@Log4j
@AllArgsConstructor
public class ReplyController {
	private ReplyService service;

	@RequestMapping(value="/{rno}", 
					method={ RequestMethod.PUT, RequestMethod.PATCH },
					consumes="application/json",
					produces={ MediaType.TEXT_PLAIN_VALUE } )
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, 
										 @PathVariable("rno") Long rno){
		log.info("ReplyController modify : " + vo);
		log.info("ReplyController rno : " + rno);
		vo.setRno(rno);
		int modifyCount =  service.modify(vo);	//댓글 수정
		log.info("REPLY MODIFY COUNT : " + modifyCount);
		
		//성공이면 200, 실패면 500 반환
		return modifyCount == 1
			   ? new ResponseEntity<>("success", HttpStatus.OK)
			   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/{rno}")	
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		log.info("ReplyController get : " + rno);

		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("ReplyController remove : " + rno);
		
		return service.remove(rno) == 1
				   ? new ResponseEntity<>("success", HttpStatus.OK)
				   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/pages/{bno}/{page}")
//	public ResponseEntity<List<ReplyVO>> getList(
	public ResponseEntity<ReplyPageDTO> getList(
							@PathVariable("page") int page,
							@PathVariable("bno") Long bno ) {
		log.info("ReplyController getList : ");
		Criteria cri = new Criteria(page, 10);
		log.info("ReplyController cri : " + cri);
//		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	@PostMapping(value="/new",
				 consumes="application/json",
				 produces= { MediaType.TEXT_PLAIN_VALUE } )
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		log.info("ReplyController create vo : " + vo);
		int insertCount =  service.register(vo);	//댓글 등록
		log.info("REPLY INSERT COUNT : " + insertCount);
		
		//성공이면 200, 실패면 500 반환
		return insertCount == 1
			   ? new ResponseEntity<>("success", HttpStatus.OK)
			   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}










