package com.emotionbank.business.api.terms.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.terms.dto.TermsDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetTermsDto {
	private final List<Response> terms;

	@Builder
	public GetTermsDto(List<Response> terms) {
		this.terms = terms;
	}

	public static GetTermsDto from(List<TermsDto> termsDto) {
		return GetTermsDto.builder()
			.terms(termsDto.stream().map(Response::from).collect(Collectors.toList()))
			.build();
	}

	@Getter
	private static class Response {
		private final Long termsId;
		private final String title;
		private final String content;
		private final String mandatory;

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
