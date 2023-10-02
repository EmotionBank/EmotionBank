package com.emotionbank.business.api.category.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.category.dto.CreateCategoryDto;
import com.emotionbank.business.api.category.dto.GetCategoryListDto;
import com.emotionbank.business.api.category.dto.UpdateCategoryDto;
import com.emotionbank.business.domain.category.dto.CategoryDto;
import com.emotionbank.business.domain.category.service.CategoryService;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryDto.Request request,
		@UserInfo UserInfoDto userInfoDto) {
		categoryService.createCategory(CategoryDto.of(request, userInfoDto.getUserId()));
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId, @UserInfo UserInfoDto userInfoDto) {
		categoryService.deleteCategory(categoryId, userInfoDto.getUserId());
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<GetCategoryListDto.Response> getCategoryList(@UserInfo UserInfoDto userInfoDto) {
		List<CategoryDto> categoryDtoList = categoryService.getCategoryList(userInfoDto.getUserId());
		return ResponseEntity.ok(GetCategoryListDto.Response.from(categoryDtoList));
	}

	@PutMapping
	public ResponseEntity<Void> updateCategory(@RequestBody UpdateCategoryDto.Request request,
		@UserInfo UserInfoDto userInfoDto) {
		categoryService.updateCategory(CategoryDto.of(request, userInfoDto.getUserId()));
		return ResponseEntity.ok().build();
	}
}
