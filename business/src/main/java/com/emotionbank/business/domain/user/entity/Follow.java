package com.emotionbank.business.domain.user.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow implements Serializable {
	@Id
	@ManyToOne
	private User follower;

	@Id
	@ManyToOne
	private User followee;
}
