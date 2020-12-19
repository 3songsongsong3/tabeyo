package com.tabeyo.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReviewVO {
		private Long rvNo;
		private Long businNo;
		private String rvTitle;
		private String rvContent;
		private String rvWriter;
		private Date regdate;
		private Date updateDate;
		
		private int reviewReplyCnt;
		
		private List<ReviewAttachVO> reviewAttachList;
}
