package com.emotionbank.business.domain.user.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.user.dto.Role;
import com.emotionbank.business.domain.user.dto.SocialType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String nickname;

	private LocalDate birthday;

	private Role role;

	private String socialId;

	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	private LocalDate createdTime;

	private LocalDate lastLoginTime;

	private LocalDate withdrawalTime;

	@OneToMany(mappedBy = "user")
	private List<Account> accounts;

	@OneToMany(mappedBy = "user")
	private List<Agreement> agreement;

	@OneToMany(mappedBy = "user")
	private List<Category> category;

	private String image;

	@OneToMany(mappedBy = "follower")
	private List<Follow> followerList;

	@OneToMany(mappedBy = "followee")
	private List<Follow> followeeList;

	// @OneToMany(mappedBy = "user")
	// private List<Calendar> calendar;

}
