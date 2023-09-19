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

	@GetMapping("/{nickname}")
	public ResponseEntity<?> searchUser(@PathVariable String nickname, Pageable pageable) {
		List<UserDto> userDtos = userService.searchUser(nickname, pageable);
		UserSearchDto.Response response = UserSearchDto.Response.from(userDtos);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/follow/{nickname}")
	public ResponseEntity<?> followUser(@PathVariable String nickname) {
		userService.followUser(FollowDto.of("followee", nickname));
		return ResponseEntity.ok().build();
	}

	@GetMapping("/followee/{nickname}")
	public ResponseEntity<?> getFollowees(@PathVariable String nickname) {
		List<UserDto> followees = userService.getFollowees(nickname);
		UserFollowsDto.Response response = UserFollowsDto.Response.from(followees);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/follower/{nickname}")
	public ResponseEntity<?> getFollowers(@PathVariable String nickname) {
		List<UserDto> followers = userService.getFollowers(nickname);
		UserFollowsDto.Response response = UserFollowsDto.Response.from(followers);
		return ResponseEntity.ok(response);
	}

}
