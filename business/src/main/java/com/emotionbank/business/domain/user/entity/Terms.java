package com.emotionbank.business.domain.user.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.emotionbank.business.domain.user.dto.Mandatory;

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

	@Column(name="create_time")
	private LocalDate createTime;

	@Column(name="update_time")
	private LocalDate updateTime;

	@OneToOne(mappedBy = "terms")
	private Agreement agreement;
}