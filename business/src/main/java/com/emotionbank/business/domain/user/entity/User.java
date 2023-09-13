package com.emotionbank.business.domain.user.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.user.dto.Type;
import com.emotionbank.business.domain.user.dto.UserDto;

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
	@Column(name = "user_id")
	private Long userId;

	private String nickname;

	@Column(name = "birthday")
	private LocalDate birthDay;

	private String email;

	@Column(name = "member_type")
	private Type memberType;

	@Column(name = "created_time")
	private LocalDate createdTime;

	@Column(name = "last_login_time")
	private LocalDate lastLoginTime;

	@Column(name = "withdrawal_time")
	private LocalDate withdrawalTime;

	@OneToMany(mappedBy = "user")
	private List<Account> accounts;

	@OneToMany(mappedBy = "user")
	private List<Agreement> agreement;

	@OneToMany(mappedBy = "user")
	private List<Category> category;

	private String image;

	// @OneToMany(mappedBy = "user")
	// private List<Calendar> calendar;
	public static UserDto.UserSearchResultDto toUserSearchResultDto(User user) {
		return UserDto.UserSearchResultDto.builder()
			.nickname(user.getNickname())
			.image(user.getImage())
			.build();
	}
}
