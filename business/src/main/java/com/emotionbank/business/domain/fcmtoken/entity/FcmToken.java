package com.emotionbank.business.domain.fcmtoken.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.emotionbank.business.domain.user.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tokenId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	private String token;

	private LocalDateTime createTime;

	@Builder
	public FcmToken(Long tokenId, User user, String token, LocalDateTime createTime) {
		this.tokenId = tokenId;
		this.user = user;
		this.token = token;
		this.createTime = createTime;
	}

	public static FcmToken of(User user, String token) {
		return FcmToken.builder()
			.user(user)
			.token(token)
			.createTime(LocalDateTime.now())
			.build();
	}
}
