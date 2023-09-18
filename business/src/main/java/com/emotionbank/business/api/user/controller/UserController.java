package com.emotionbank.business.api.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.user.dto.response.UserSearchResponseDto;
import com.emotionbank.business.domain.user.dto.UserSearchResultDto;
import com.emotionbank.business.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/{nickname}")
	public ResponseEntity<?> searchUser(@PathVariable String nickname, Pageable pageable) {
		List<UserSearchResultDto> userSearchResultDtos = userService.searchUser(nickname, pageable);
		List<UserSearchResponseDto> userSearchResponseDtos = userSearchResultDtos.stream()
			.map(UserSearchResponseDto::of)
			.collect(Collectors.toList());
		return ResponseEntity.ok(userSearchResponseDtos);
	}

	@PostMapping("/follow/{nickname}")
	public ResponseEntity<?> followUser(@PathVariable String nickname) {
		userService.followUser(nickname);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/followee/{nickname}")
	public ResponseEntity<?> getFollowees(@PathVariable String nickname) {
		userService.getFollowees(nickname);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/follower/{nickname}")
	public ResponseEntity<?> getFollowers(@PathVariable String nickname) {
		userService.getFollowers(nickname);
		return ResponseEntity.ok().build();
	}

}
