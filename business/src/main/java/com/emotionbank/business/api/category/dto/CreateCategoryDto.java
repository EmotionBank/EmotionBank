package com.emotionbank.business.api.category.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateCategoryDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private Long userId;
		private String categoryName;
		private String visibility;

		@Builder
		public Request(Long userId, String categoryName, String visibility) {
			this.userId = userId;
			this.categoryName = categoryName;
			this.visibility = visibility;
		}
		
	}

}
