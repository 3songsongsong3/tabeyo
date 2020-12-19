package com.tabeyo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewReplyVO {
	private Long rvRno;
	private Long rvNo;
	private String rvReply;
	private String rvRWriter;
	private Date regdate;
	private Date updateDate;

}
