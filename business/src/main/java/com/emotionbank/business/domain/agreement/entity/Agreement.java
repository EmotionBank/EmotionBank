package com.emotionbank.business.domain.agreement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.emotionbank.business.domain.agreement.constant.State;
import com.emotionbank.business.domain.terms.entity.Terms;
import com.emotionbank.business.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agreement_id")
	private Long agreementId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "terms_id")
	private Terms terms;

	private State state;

	@Column(name = "agreement_time")
	private LocalDate agreementTime;
}
