package com.tabeyo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tabeyo.service.BusinReportService;
import com.tabeyo.service.FeedReportService;
import com.tabeyo.service.FeedRpReportService;
import com.tabeyo.service.FreeReportService;
import com.tabeyo.service.ReplyReportService;
import com.tabeyo.service.ReviewReportService;
import com.tabeyo.service.ReviewRpReportService;
import com.tabeyo.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//@Controller
@Log4j
@RequestMapping("/report/")
@AllArgsConstructor
public class ReportController {
	private BusinReportService businReportService;
	private FeedReportService feedReportService;
	private FeedRpReportService feedRpReportService;
	private FreeReportService freeReportService;
	private ReplyReportService replyReportService;
	private ReviewReportService reviewReportService;
	private ReviewRpReportService ReviewRpReportService;
	private UserService userService;
	
	
	//신고하기 (SHG_003) 화면
	@GetMapping("/register")
	public void register() {
		
	}
	
	//신고하기 (SHG_003) 처리
	@PostMapping("/register")
	public String register(int a) {
		
		return null; 
	}
	// 소비자 사업자 신고 목록 페이지 (SHG_004)
	@GetMapping("/reportList")
	public void reportList() {
		
	}
	
	// 관리자 신고 목록 페이지 (KAN_010)
	@GetMapping("/list")
	public void getList() {
	
	}
	
	// 관리자 신고 목록 페이지 신고 처리 (KAN_010)
	@PostMapping("/list")
	public String list() {
		
		userService.pointModify(null, null, null);

		userService.forceRemove(null, null);
		
		return "redirect:/report/list";
		
	}
	
	
	
}
