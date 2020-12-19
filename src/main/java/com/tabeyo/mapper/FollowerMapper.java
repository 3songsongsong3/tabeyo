package com.tabeyo.mapper;

import java.util.List;

import com.tabeyo.domain.FollowerVO;

public interface FollowerMapper {
	
	//팔로우 하기
	void follow(FollowerVO follow);
	
	//언팔로우 하기
	void unfollow(FollowerVO follow);
	
	//팔로우 유무
	int isFollow(FollowerVO follow);
	
	//팔로우 리스트 조회
	//List<FollowerVO> selectFollowingList(String userId);
	
	//팔로워 리스트 조회
	List<FollowerVO> selectFollowerList(String userId);
	
	
}
