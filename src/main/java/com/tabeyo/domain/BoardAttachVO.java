package com.tabeyo.domain;

import lombok.Data;

@Data
public class BoardAttachVO {
	private String fileName;	//원본 파일명
	private String uploadPath;	//업로드 경로
	private String uuid;		//UUID 값
	private boolean fileType;	//이미지 여부
	private Long bno;
}















