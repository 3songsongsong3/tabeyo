package com.tabeyo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReviewReplyPageDTO {
	private List<ReviewReplyVO> list;	//댓글목록
	private int replyCnt;		//전체 댓글 수
	

}
