package com.emotionbank.business.api.terms.dto;

import com.emotionbank.business.domain.terms.dto.TermsDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TermsCreateDto {
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String title;
		private String content;
		private String mandatory;

		@Builder
		public Request(String title, String content, String mandatory) {
			this.title = title;
			this.content = content;
			this.mandatory = mandatory;
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private Long termsId;
		private String title;
		private String content;
		private String mandatory;

		@Builder
		public Response(Long termsId, String title, String content, String mandatory) {
			this.termsId = termsId;
			this.title = title;
			this.content = content;
			this.mandatory = mandatory;
		}

		public static Response from(TermsDto termsDto) {
			return Response.builder()
				.termsId(termsDto.getTermsId())
				.title(termsDto.getTitle())
				.content(termsDto.getContent())
				.mandatory(termsDto.getMandatory().name())
				.build();
		}
	}
}
