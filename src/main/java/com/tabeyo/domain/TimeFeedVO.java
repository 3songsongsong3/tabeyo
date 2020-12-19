package com.tabeyo.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TimeFeedVO {
	
	private List<TimeFeedAttachVO> attachList;
	private Long replyCnt;
	private Long fdNo;
	private String fdContent;
	private String userId;
	private String nickname;
	private Date regDate;
	private Date updateDate;
	private int likeCount;
	// 2020.12.13 추가 
	private String writer;

	
}
