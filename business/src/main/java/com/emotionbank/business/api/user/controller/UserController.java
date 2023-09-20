package com.emotionbank.business.api.user.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.user.dto.UserFollowsDto;
import com.emotionbank.business.api.user.dto.UserInfoDto;
import com.emotionbank.business.api.user.dto.UserSearchDto;
import com.emotionbank.business.domain.user.dto.FollowDto;
import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<UserInfoDto.Response> myInfo() {
		// todo : 유저 가지고오기
		UserDto userInfo = userService.getUserInfo(1L);
		return ResponseEntity.ok(UserInfoDto.Response.from(userInfo));
	}

	@GetMapping("/{nickname}")
	public ResponseEntity<UserSearchDto.Response> searchUser(@PathVariable String nickname, Pageable pageable) {
		List<UserDto> userDtos = userService.searchUser(nickname, pageable);
		UserSearchDto.Response response = UserSearchDto.Response.from(userDtos);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/follow/{userId}")
	public ResponseEntity<?> followUser(@PathVariable Long userId) {
		userService.followUser(FollowDto.of(1L, userId));
		return ResponseEntity.ok().build();
	}

	@GetMapping("/followee/{userId}")
	public ResponseEntity<UserFollowsDto.Response> getFollowees(@PathVariable Long userId) {
		List<UserDto> followees = userService.getFollowees(userId);
		UserFollowsDto.Response response = UserFollowsDto.Response.from(followees);
		return ResponseEntity.ok(response);
	}

}
