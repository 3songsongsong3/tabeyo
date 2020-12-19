package com.tabeyo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tabeyo.domain.ReviewAttachVO;
import com.tabeyo.domain.ReviewVO;
import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.PageDTO;
import com.tabeyo.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//@Controller
@Log4j
@RequestMapping("/review/*")
@AllArgsConstructor
public class ReviewController {
	private ReviewService reviewService;

	// 첨부 파일 삭제
	private void deleteFiles(List<ReviewAttachVO> attachList) {
		if (attachList == null || attachList.size() == 0) {
			return;
		}

		attachList.forEach(attach -> {
			Path file = Paths.get(
					"c:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getRvFileName());

			try {
				Files.deleteIfExists(file); // 파일이 존재하면 삭제

				if (Files.probeContentType(file).startsWith("image")) {
					// 이미지이면 섬네일 삭제
					file = Paths.get("c:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_"
							+ attach.getRvFileName());
					Files.delete(file);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	// 첨부파일 목록 JSON 반환
	@GetMapping("/getAttachList")
	@ResponseBody
	public ResponseEntity<List<ReviewAttachVO>> getAttachList(Long rvNo) {
		log.info("reviewController...getAttachList()");
		return new ResponseEntity<>(reviewService.getAttachList(rvNo), HttpStatus.OK);
	}

	// 리뷰 목록 가져오기
	@GetMapping("/list")
	public void list(Long businNo, Criteria cri, Model model) {
		log.info("reviewController...list() with cri : " + cri);

		int total = reviewService.getTotalCount(cri);
		model.addAttribute("list", reviewService.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(total, cri));
	}
	

	// 리뷰 조회 또는 수정
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("rvNo") Long rvNo, @RequestParam("businNo") Long businNo,
			@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("reviewController...get() or modify()");
		model.addAttribute("review", reviewService.get(rvNo, businNo));
	}

	// 리뷰 등록 화면 이동
	@GetMapping("/register")
	public void register() {
	}

	// 리뷰 등록 처리
	@PostMapping("/register")
	public String register(ReviewVO review, RedirectAttributes rttr) {
		log.info("reviewController...register()");

		log.info("---------------------------");
		if (review.getReviewAttachList() != null) {
			review.getReviewAttachList().forEach(attach -> log.info(attach));
		}
		log.info("---------------------------");

		reviewService.register(review);
		rttr.addFlashAttribute("result", review.getRvNo());
		return "redirect:/review/list";
	}

	// 리뷰 수정
	@PostMapping("/modify")
	public String modify(ReviewVO review, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("reviewController...modify()");

		if (reviewService.modify(review)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/review/list" + cri.getListLink();
	}
	
	// 리뷰 삭제
		@PostMapping("/remove")
		public String remove(String rvWriter, @RequestParam("rvNo") Long rvNo, @RequestParam("businNo") Long businNo,@ModelAttribute("cri") Criteria cri,
				RedirectAttributes rttr) {
			log.info("reviewController...remove()");

			// 첨부파일 목록 가져오기
			List<ReviewAttachVO> attachList = reviewService.getAttachList(rvNo);

			if (reviewService.remove(rvNo,businNo)) {
				deleteFiles(attachList); // 첨부파일 삭제
				rttr.addFlashAttribute("result", "success");
			}

			return "redirect:/review/list" + cri.getListLink();
		}


}