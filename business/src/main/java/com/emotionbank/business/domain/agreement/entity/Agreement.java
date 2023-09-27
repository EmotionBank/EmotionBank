package com.emotionbank.business.domain.agreement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.emotionbank.business.domain.agreement.constant.State;
import com.emotionbank.business.domain.terms.entity.Terms;
import com.emotionbank.business.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
	@NotNull
	private User user;

	@OneToOne
	@JoinColumn(name = "terms_id")
	@NotNull
	private Terms terms;

	@NotNull
	@Enumerated(EnumType.STRING)
	private State state;

	@Column(name = "agreement_time")
	private LocalDateTime agreementTime;

	public static Agreement newSignUpAgreement(User user, Terms terms) {
		return Agreement.builder()
			.user(user)
			.terms(terms)
			.state(State.PENDING)
			.build();
	}

	public void updateState(State state) {
		this.state = state;
	}

	public void updateAgreementTime(LocalDateTime agreementTime) {
		this.agreementTime = agreementTime;
	}
}
