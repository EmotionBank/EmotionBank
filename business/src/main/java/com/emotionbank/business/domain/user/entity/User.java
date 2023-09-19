package com.emotionbank.business.domain.user.entity;

import java.time.LocalDate;
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

import org.springframework.data.annotation.CreatedDate;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.constant.SocialType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private LocalDate birthday;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(nullable = false)
	private String socialId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	@CreatedDate
	private LocalDateTime createdTime;

	private LocalDateTime lastLoginTime;

	private LocalDateTime withdrawalTime;

	@OneToMany(mappedBy = "user")
	private List<Account> accounts;

	@OneToMany(mappedBy = "user")
	private List<Agreement> agreement;

	@OneToMany(mappedBy = "user")
	private List<Category> category;

	private String image;

	@Builder
	public User(Long userId, String nickname, LocalDate birthday, Role role, String socialId, SocialType socialType,
		LocalDateTime createdTime, LocalDateTime lastLoginTime, LocalDateTime withdrawalTime, String image) {
		this.userId = userId;
		this.nickname = nickname;
		this.birthday = birthday;
		this.role = role;
		this.socialId = socialId;
		this.socialType = socialType;
		this.createdTime = createdTime;
		this.lastLoginTime = lastLoginTime;
		this.withdrawalTime = withdrawalTime;
		this.image = image;
	}

	public static User of(String id, SocialType socialType) {
		return User.builder()
			.nickname("")
			.birthday(LocalDate.now())
			.role(Role.PENDING)
			.socialId(id)
			.socialType(socialType)
			.build();
	}

	public void updateWithdrawalTime(LocalDateTime withdrawalTime) {
		this.withdrawalTime = withdrawalTime;
	}

	public void updateLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	// @OneToMany(mappedBy = "user")
	// private List<Calendar> calendar;

}
