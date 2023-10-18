package com.emotionbank.business.domain.user.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.emotionbank.business.domain.user.dto.FeedsDto;
import com.emotionbank.business.domain.user.dto.FollowDto;
import com.emotionbank.business.domain.user.dto.ReportDto;
import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

public interface UserService {
	List<UserDto> searchUser(String userNickname);

	boolean followUser(FollowDto followDto);

	List<UserDto> getFollowees(Long userId);

	List<UserDto> getFollowers(Long userId);

	UserDto getUserInfo(Long userId);

	UserDto getMyProfile(Long userId);

	UserDto getOtherProfile(Long userId);

	void updateUser(UserDto request);

	boolean checkDuplicateNickname(String nickname);

	boolean checkAdminRole(UserInfoDto userInfoDto);

	String getNickname(Long userId);

	boolean isFollow(Long followerId, Long followeeId);

	ReportDto getReport(Long userId);

	FeedsDto getFeed(Long userId);
}
