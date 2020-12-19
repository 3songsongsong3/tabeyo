package com.tabeyo.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int	startPage;		//시작 페이지
	private int endPage;		//끝 페이지
	private boolean prev, next;	//이전, 다음
	private int total;			//전체 게시물 수
	private Criteria cri;		//Criteria
	
	public PageDTO(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		
		//끝 페이지 계산
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)) * 10;
		
		//시작 페이지 계산
		this.startPage = this.endPage - 9;
		
		//전체 데이터 수를 반영하여 실제 끝 페이지 계산
		int realEnd = (int)(Math.ceil( (total * 1.0) / cri.getAmount()));
		
		//끝 페이지가 실제 끝 페이지보다 큰 경우
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;		//이전 페이지
		this.next = this.endPage < realEnd; //다음 페이지
	}
}












