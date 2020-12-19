package com.tabeyo.domain;

import lombok.Data;

@Data
public class FollowingVO {
	
	private String followingUser;
	private String userId;
	private String followingUserId;
}
