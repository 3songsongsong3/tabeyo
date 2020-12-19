package com.tabeyo.controller;

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
import com.tabeyo.domain.ReviewReplyPageDTO;
import com.tabeyo.domain.ReviewReplyVO;
import com.tabeyo.service.ReviewReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//@RestController
@Log4j
@AllArgsConstructor
public class ReviewReplyController {
	// 리뷰댓글 서비스
	private ReviewReplyService reviewReplyService;

	// 리뷰댓글 리스트 가져오기
	@GetMapping("/{businNo}/pages/{rvNo}/{page}")
	public ResponseEntity<ReviewReplyPageDTO> getList(@PathVariable("businNo") Long businNo,
			@PathVariable("page") int page, @PathVariable("rvNo") Long rvNo) {
		log.info("ReplyController getList : ");
		Criteria cri = new Criteria(page, 10);
		log.info("ReplyController cri : " + cri);
		return new ResponseEntity<>(reviewReplyService.getListPage(cri, rvNo), HttpStatus.OK);
	}

	// 리뷰댓글 작성
	@PostMapping(value = "/register", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReviewReplyVO vo) {
		log.info("ReviewReplyController create vo : " + vo);
		int insertCount = reviewReplyService.register(vo); // 댓글 등록
		log.info("REPLY INSERT COUNT : " + insertCount);

		// 성공이면 200, 실패면 500 반환
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 리뷰댓글 수정
	@RequestMapping(value = "/{rvRno}", method = { RequestMethod.PUT,
			RequestMethod.PATCH }, consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })

	public ResponseEntity<String> modify(@RequestBody ReviewReplyVO vo, @PathVariable("rvRno") Long rvRno) {
		log.info("ReplyController modify : " + vo);
		log.info("ReplyController rvRno : " + rvRno);
		vo.setRvRno(rvRno);
		int modifyCount = reviewReplyService.modify(vo); // 댓글 수정
		log.info("REPLY MODIFY COUNT : " + modifyCount);

		// 성공이면 200, 실패면 500 반환
		return modifyCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 리뷰댓글 삭제
	@DeleteMapping("/{rvRno}")
	public ResponseEntity<String> remove(@PathVariable("rvRno") Long rvRno, @RequestBody ReviewReplyVO vo) {
		log.info("ReplyController remove : " + rvRno);

		return reviewReplyService.remove(rvRno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}