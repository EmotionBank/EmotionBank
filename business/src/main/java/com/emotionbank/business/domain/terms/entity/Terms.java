package com.emotionbank.business.domain.terms.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.emotionbank.business.domain.agreement.entity.Agreement;
import com.emotionbank.business.domain.terms.constant.Mandatory;
import com.emotionbank.business.domain.terms.dto.TermsDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Terms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "terms_id")
	private Long termsId;
	@NotNull
	private String title;
	@NotNull
	private String content;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Mandatory mandatory;
	@Column(name = "create_time")
	@NotNull
	private LocalDateTime createTime;
	@Column(name = "update_time")
	private LocalDateTime updateTime;
	@OneToMany(mappedBy = "terms")
	private List<Agreement> agreement;

	@Builder
	public Terms(Long termsId, String title, String content, Mandatory mandatory, LocalDateTime createTime,
		LocalDateTime updateTime) {
		this.termsId = termsId;
		this.title = title;
		this.content = content;
		this.mandatory = mandatory;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public static Terms from(TermsDto termsDto) {
		return Terms.builder()
			.title(termsDto.getTitle())
			.content(termsDto.getContent())
			.mandatory(termsDto.getMandatory())
			.createTime(LocalDateTime.now())
			.build();
	}
}
