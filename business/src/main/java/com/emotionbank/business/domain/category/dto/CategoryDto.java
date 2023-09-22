package com.emotionbank.business.domain.category.dto;

import com.emotionbank.business.api.category.dto.CreateCategoryDto;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.transaction.constant.Visibility;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryDto {
	private Long categoryId;
	private Long userId;
	private String categoryName;
	private Visibility visibility;

	@Builder
	public CategoryDto(Long categoryId, Long userId, String categoryName, Visibility visibility) {
		this.categoryId = categoryId;
		this.userId = userId;
		this.categoryName = categoryName;
		this.visibility = visibility;
	}

	public static CategoryDto of(CreateCategoryDto.Request request, Long userId) {
		return CategoryDto.builder()
			.userId(userId)
			.categoryName(request.getCategoryName())
			.visibility(Visibility.valueOf(request.getVisibility()))
			.build();
	}

	public static CategoryDto from(Category category) {
		return CategoryDto.builder()
			.categoryId(category.getCategoryId())
			.categoryName(category.getCategoryName())
			.userId(category.getUser().getUserId())
			.visibility(category.getVisibility())
			.build();
	}
}
