package com.emotionbank.business.api.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.user.dto.response.UserResponseDto;
import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	@GetMapping("/{nickname}")
	public ResponseEntity<?> searchUser(@PathVariable String nickname,Pageable pageable) {
		List<UserDto.UserSearchResultDto> userSearchResultDtos = userService.searchUser(nickname,pageable);
		List<UserResponseDto.UserSearchResponseDto> userSearchResponseDtos = userSearchResultDtos.stream()
			.map(UserResponseDto.UserSearchResponseDto::of)
			.collect(Collectors.toList());
		return new ResponseEntity<>(userSearchResponseDtos,HttpStatus.OK);
	}
	@PostMapping("/follow/{nickname}")
	public ResponseEntity<?> followUser(@PathVariable String nickname) {
		userService.followUser(nickname);
	return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/followee/{nickname}")
	public ResponseEntity<?> getFollowees(@PathVariable String nickname) {
		userService.getFollowees(nickname);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/follower/{nickname}")
	public ResponseEntity<?> getFollowers(@PathVariable String nickname) {
		userService.getFollowers(nickname);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
