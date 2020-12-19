package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.FollowerVO;
import com.tabeyo.domain.FollowingVO;
import com.tabeyo.domain.UserVO;
import com.tabeyo.mapper.FollowerMapper;
import com.tabeyo.mapper.FollowingMapper;

import lombok.Setter;

@Service
public class FollowingServiceImpl implements FollowingService {

	@Setter(onMethod_ = @Autowired)
	private FollowerMapper followerMapper;
	@Setter(onMethod_ = @Autowired)
	private FollowingMapper followingMapper;
	
	@Override
	public void follow(FollowingVO follow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfollow(FollowingVO follow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isFollow(FollowingVO follow) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<FollowingVO> selectFollowingList(String userId) {
		// TODO Auto-generated method stub
		return followingMapper.selectFollowingList(userId);
	}

	@Override
	public UserVO selectFollowingId(String followingUser) {
		// TODO Auto-generated method stub
		return followingMapper.selectFollowingId(followingUser);
	}


}
