package com.tabeyo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tabeyo.domain.PageDTO;

import com.tabeyo.controller.TabestoryController;
import com.tabeyo.domain.Criteria;
import com.tabeyo.domain.FollowerVO;
import com.tabeyo.domain.FollowingVO;
import com.tabeyo.domain.TimeFeedVO;
import com.tabeyo.domain.UserVO;
import com.tabeyo.mapper.FollowerMapper;
import com.tabeyo.mapper.FollowingMapper;
import com.tabeyo.mapper.TimeFeedMapper;
import com.tabeyo.service.FollowerService;
import com.tabeyo.service.FollowingService;
import com.tabeyo.service.TimeFeedService;
import com.tabeyo.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/tabeStory/*")
@AllArgsConstructor
public class TabestoryController {
	private TimeFeedService timeFeedService;
	private FollowingService followingService;
	private FollowerService followerService;
	
	//타임라인 목록 
	@GetMapping("/timelineList")
	public void timelinelist(@RequestParam 	String userId,Model model) {
		log.info("TabeStoryController...timelineList()");

		UserVO userVO = new UserVO();
		
		userVO = timeFeedService.viewMember(userId);
				
		model.addAttribute("userVO", userVO);
		
		log.info("타임라인 리스트 userID = "+timeFeedService.getUserId(userId));
		
		
	
		 model.addAttribute("list", timeFeedService.getList(userId));
		 
	
	}
	
	//타베모노가타리 페이지 (유저정보) (userService.read) 
	@RequestMapping(value="/get"	, method= RequestMethod.GET) 
		public void read(@RequestParam 	String userId,  Model model) {
		
		UserVO userVO = new UserVO();
		
		userVO = timeFeedService.viewMember(userId);
		
		
		model.addAttribute("userVO", userVO);
		
		
	}
	

	// 팔로잉/팔로워 목록 출력 (팔로잉, 팔로워 아마 ok?)
	@GetMapping("/followList")
	public void followList(@RequestParam String userId,@RequestParam(required=false) String follow ,Model model) {
		log.info("TabeStoryController...followlist() userId : " + userId);
	
		log.info("-------------------------");
		
		UserVO userVO = new UserVO();
		
		userVO = timeFeedService.viewMember(userId);

		// following 유저의 아이디 
		followingService.selectFollowingId(follow);

		
		model.addAttribute("userVO", userVO);
		
		log.info(userVO);
		log.info(followerService.selectFollowerList(userId));
		log.info(followingService.selectFollowingList(userId));
		// followlist 접근(매핑)시 팔로잉 유저, 팔로워 유저의 닉네임을 매개변수로서 바로 가져오지 못함. 
		//
		
		model.addAttribute("following", followingService.selectFollowingList(userId));
		model.addAttribute("follower", followerService.selectFollowerList(userId));

	}
	
	/*
	 * @GetMapping("/get/{userId}") public void read(String userId, Model model) {
	 * log.info("TabeStoryController...get()"+userId); model.addAttribute("dto",
	 * userService.viewMember(userId));
	 * 
	 * }
	 */
	
	
	//타베모노가타리 페이지 (팔로우) ok
		@PostMapping("/follow")
		public String follow(FollowingVO follow) {
			log.info("TabeStoryController...follow()");

			
			
			return "redirect:/tabeStory/get";
		}
		
		//타베모노가타리 페이지 (언팔로우) ok
		@PostMapping("/unfollow")
		public String unfollow(FollowingVO follow) {
			log.info("TabeStoryController...unfollow()");

			
			
			return "redirect:/tabeStory/get";
		}
		
	
	
	
	
	//타베모노가타리 타임라인 리뷰 상세보기 (reviewService.get)
	@GetMapping("/getReview")
	public void get() {
		log.info("TabeStoryController...getReview()");
	
		
	}
	
	//타베모노가타리 타임라인 피드 상세보기 (service ok)  
	@GetMapping("/feed")
	public void feed(
					@RequestParam("fdNo")Long fdNo, 
					@ModelAttribute("cri")Criteria cri, Model model) {
		
		log.info("TabeStoryController...feed()");
		

		model.addAttribute("feed", timeFeedService.feed(fdNo));
	}
	
	// 타베모노가타리 타임라인 피드 수정 화면 (service ok)
	@GetMapping("/modify")
	public void modify(@RequestParam("fdNo") Long fdNo, 
			Model model) {
		log.info("TabeStoryController...modify()");

		model.addAttribute("feed", timeFeedService.feed(fdNo));
	}
	
	// 타베모노가타리 타임라인 피드 수정 처리 (service ok)
	@PostMapping("/modify")
	// Post 방식에는 @RequestParam 사용 불가 @RequestBody쓸것
//	public String modify(@RequestBody Long fdNo) {
	public String modify(TimeFeedVO vo,RedirectAttributes rttr) {
		log.info("TabeStoryController...modify() - post");
		log.info(vo.getUserId());
		log.info(vo.getWriter());
		log.info(vo.getFdContent());
		log.info(vo.getFdNo());


		if(timeFeedService.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		} else {
			log.info("실패...ㅠㅠ");
		}
		 
		rttr.addFlashAttribute("result", vo.getFdNo());
		rttr.addAttribute("userId",vo.getUserId());
		
		
		return "redirect:/tabeStory/timelineList?userId={userId}";
	}
	
	// 타베모노가타리 타임라인 피드 삭제 처리 (service ok)
		@PostMapping("/remove")
		public String remove(TimeFeedVO fdNo,
									RedirectAttributes rttr) {
			log.info("TabeStoryController...remove()");

			
			rttr.addAttribute("userId",fdNo.getUserId());
			rttr.addFlashAttribute("result", fdNo.getFdNo());

			if(timeFeedService.remove(fdNo)) {
				rttr.addFlashAttribute("result", "success");
			}
			
			return "redirect:/tabeStory/timelineList?userId={userId}";
		}
	
		
		
	//타베모노가타리 타임라인 피드 작성 화면 (service ok)
	@GetMapping("/register")
	public void register() {
		log.info("TabeStoryController...register() GET");
		
		
		/*
		 * model.addAttribute("userId", timeFeedService.getUserId(userId));
		 * log.info(timeFeedService.getUserId(userId));
		 */
		
	}
	
	// 타베모노가타리 타임라인 피드 작성 처리  (service ok)
	@PostMapping("/register")
	//@ResponseBody 
	// Post 방식에는 @RequestParam 사용 불가 @RequestBody쓸것
	public String register(TimeFeedVO vo,RedirectAttributes rttr) {
		log.info("TabeStoryController...register() POST "+vo.getUserId());
		
		rttr.addFlashAttribute("result", vo.getFdNo());
		rttr.addAttribute("userId",vo.getUserId());
		
		 // model.addAttribute("list", timeFeedService.getList(userId));
		// addFlashAttribute 리프레시하면 데이터가 소멸
		/*
		 * UserVO id = new UserVO();
		 * 
		 * id = timeFeedService.getUserId(userId);
		 */
		  timeFeedService.register(vo);
		
		return "redirect:/tabeStory/timelineList?userId={userId}";
	}
	
	
	
	
	
	
}
