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

import com.tabeyo.domain.BoardAttachVO;
import com.tabeyo.domain.BoardVO;
import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.PageDTO;
import com.tabeyo.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;

	//첨부 파일 삭제 
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		attachList.forEach(attach -> {
			Path file = Paths.get("c:\\upload\\" + 
								  attach.getUploadPath() + "\\" +
								  attach.getUuid() + "_" +
								  attach.getFileName());
			
			try {
				Files.deleteIfExists(file);	//파일이 존재하면 삭제
				
				if(Files.probeContentType(file).startsWith("image")) {
					//이미지이면 섬네일 삭제
					file = Paths.get("c:\\upload\\" + 
								  attach.getUploadPath() + "\\s_" +
								  attach.getUuid() + "_" +
								  attach.getFileName());
					Files.delete(file);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		});
	}
	
	@PostMapping("/remove")		//게시물 삭제
	public String remove(@RequestParam("bno") Long bno,
		     			 @ModelAttribute("cri") Criteria cri,
						 RedirectAttributes rttr) {
		log.info("BoardController...remove()");
		
		//첨부파일 목록 가져오기
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		if(service.remove(bno)) {
			deleteFiles(attachList);	//첨부파일 삭제
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list" + cri.getListLink();
	}
	
	
	//첨부파일 목록 JSON 반환
	@GetMapping("/getAttachList")
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		log.info("BoardController...getAttachList()");
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
	}

	
	@PostMapping("/modify")		//게시물 수정
	public String modify(BoardVO board, 
					     @ModelAttribute("cri") Criteria cri,
					     RedirectAttributes rttr) {
		log.info("BoardController...modify()");
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@GetMapping({"/get", "/modify"})			//게시물 조회 또는 수정
	public void get(@RequestParam("bno") Long bno, 
				    @ModelAttribute("cri") Criteria cri,
				    Model model) {
		log.info("BoardController...get() or modify()");
		model.addAttribute("board", service.get(bno));
	}
	
	
	@GetMapping("/register")		//게시물 등록 화면 이동
	public void register() { }
	
	@PostMapping("/register")		//게시물 등록 처리
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("BoardController...register()");
		
		log.info("---------------------------");
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		log.info("---------------------------");
		
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/list")//전체 목록 가져오기 
	public void list(Criteria cri, Model model) {
		log.info("BoardController...list() with cri : " + cri);
		
		int total = service.getTotalCount(cri);
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(total, cri));
	}
//	public void list(Model model) {
	//	log.info("BoardController...list()");
	//	model.addAttribute("list", service.getList());
//}
}












