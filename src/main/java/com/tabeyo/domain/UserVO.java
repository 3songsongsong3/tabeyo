package com.tabeyo.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserVO {
		private String userId;
	   private String userNm;
	   private String userPw;
	   private String nick; 
	   private Date birthDate;
	   private char sex;
	   private String email;
	   private String phoneNo;
	   private String addr1;
	   private String addr2;
	   private String addr3;
	   private Date regDate;
	   private String grade; 
	   private int userPoint;
	   private String auth; 
	   private String fvrFood; 
	   private String fvrRes; 
	   private String pfContent;
	   private List<UserAttachVO> attachList;
	//  private List<AuthVO> authList;


}