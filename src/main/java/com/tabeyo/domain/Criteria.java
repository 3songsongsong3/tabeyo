package com.tabeyo.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	private int pageNum;	//페이지 번호
	private int amount;		//한 페이지의 출력 게시물 수
	private String type;	//검색 타입(제목/내용/제목 + 내용)
	private String keyword;	//검색 키워드
	
	public Criteria() {
		//기본 페이지 번호는 1번, 게시물 수는 10개로 지정
		pageNum = 1;
		amount = 10;
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//검색 타입 배열로 처리
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	//링크 생성 처리
	public String getListLink() {
		UriComponentsBuilder builder 
		= UriComponentsBuilder.fromPath("")
						 	  .queryParam("pageNum", this.pageNum)
						 	  .queryParam("amount", this.amount)
						 	  .queryParam("type", this.type)
						 	  .queryParam("keyword", this.keyword);
		
		return builder.toUriString();
	}
}












