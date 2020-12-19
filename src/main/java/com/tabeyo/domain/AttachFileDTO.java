package com.tabeyo.domain;

import lombok.Data;

@Data
public class AttachFileDTO {
	private String fileName;	//원본 파일명
	private String uploadPath;	//업로드 경로
	private String uuid;		//UUID 값
	private boolean image;		//이미지 여부
}










