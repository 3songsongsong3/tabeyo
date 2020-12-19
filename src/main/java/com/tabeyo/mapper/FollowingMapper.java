package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.FollowingVO;
import com.tabeyo.domain.UserVO;


public interface FollowingMapper {
	
		//팔로우 하기
		void follow(FollowingVO follow);
		
		//언팔로우 하기
		void unfollow(FollowingVO follow);
		
		//팔로우 유무
		int isFollow(FollowingVO follow);
		
		//팔로잉 리스트 조회
		List<FollowingVO> selectFollowingList(String userId);
		
		//팔로잉 유저 조회
		public UserVO selectFollowingId(String followingUser);
		


		
		 }
