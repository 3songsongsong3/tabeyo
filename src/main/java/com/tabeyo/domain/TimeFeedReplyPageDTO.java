package com.tabeyo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class TimeFeedReplyPageDTO {
	private List<TimeFeedReplyVO> list;	//댓글 목록
	private int replyCnt;		//전체 댓글 수
}





