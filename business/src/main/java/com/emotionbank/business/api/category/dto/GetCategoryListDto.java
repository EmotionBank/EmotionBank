package com.emotionbank.business.api.category.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.category.dto.CategoryDto;
import com.emotionbank.business.domain.transaction.constant.Visibility;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GetCategoryListDto {
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Info {
		private Long categoryId;
		private String categoryName;
		private Visibility visibility;

		@Builder
		public Info(Long categoryId, String categoryName, Visibility visibility) {
			this.categoryId = categoryId;
			this.categoryName = categoryName;
			this.visibility = visibility;
		}

		public static Info from(CategoryDto categoryDto) {
			return Info.builder()
				.categoryId(categoryDto.getCategoryId())
				.categoryName(categoryDto.getCategoryName())
				.visibility(categoryDto.getVisibility())
				.build();
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private List<Info> categoryInfoList;

		@Builder
		public Response(List<Info> categoryInfoList) {
			this.categoryInfoList = categoryInfoList;
		}

		public static Response from(List<CategoryDto> categoryDtoList) {
			return Response.builder()
				.categoryInfoList(categoryDtoList.stream()
					.map(Info::from)
					.collect(Collectors.toList())
				)
				.build();
		}
	}
}
