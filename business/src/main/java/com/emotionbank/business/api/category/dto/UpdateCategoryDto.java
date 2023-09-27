package com.emotionbank.business.api.category.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateCategoryDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private Long categoryId;
		private String categoryName;
		private String visibility;

		@Builder
		public Request(Long categoryId, String categoryName, String visibility) {
			this.categoryId = categoryId;
			this.categoryName = categoryName;
			this.visibility = visibility;
		}
	}
}
