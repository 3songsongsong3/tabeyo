package com.tabeyo.domain;

import lombok.Data;

@Data
public class UserAttachVO {
	
	private String uuid;
	private String userId;
	private String fileName;
	private String uploadPath;

}
