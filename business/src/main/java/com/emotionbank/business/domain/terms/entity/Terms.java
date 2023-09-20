package com.emotionbank.business.domain.terms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.emotionbank.business.domain.agreement.entity.Agreement;
import com.emotionbank.business.domain.terms.constant.Mandatory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Terms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "terms_id")
	private Long termsId;

	private String title;

	private String content;

	private Mandatory mandatory;

	@Column(name = "create_time")
	private LocalDate createTime;

	@Column(name = "update_time")
	private LocalDate updateTime;

	@OneToOne(mappedBy = "terms")
	private Agreement agreement;
}
