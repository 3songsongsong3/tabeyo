package com.tabeyo.domain;

import lombok.Data;

@Data
public class ReviewAttachVO {
	
	private String uuid;
	private String uploadPath;
	private String rvFileName;
	
	private Long rvNo;

}
