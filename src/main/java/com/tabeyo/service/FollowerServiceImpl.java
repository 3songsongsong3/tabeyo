package com.tabeyo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabeyo.domain.FollowerVO;
import com.tabeyo.mapper.FeedRpReportMapper;
import com.tabeyo.mapper.FollowerMapper;
import com.tabeyo.mapper.FollowingMapper;

import lombok.Setter;

@Service
public class FollowerServiceImpl implements FollowerService {
	
	@Setter(onMethod_ = @Autowired)
	private FollowerMapper followerMapper;
	@Setter(onMethod_ = @Autowired)
	private FollowingMapper followingMapper;
	
	@Override
	public void follow(FollowerVO follow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfollow(FollowerVO follow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isFollow(FollowerVO follow) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<FollowerVO> selectFollowerList(String userId) {
		// TODO Auto-generated method stub
		return followerMapper.selectFollowerList(userId);
	}

}
