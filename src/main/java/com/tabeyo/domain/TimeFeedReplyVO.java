package com.tabeyo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TimeFeedReplyVO {
	
	private Long fdNo;
	private Long fdRpNo;
	private String fdRpContent;
	private String nickname;
	private Date regDate;
	private Date updateDate;
}
