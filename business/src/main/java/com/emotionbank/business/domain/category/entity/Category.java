package com.emotionbank.business.domain.category.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.emotionbank.business.domain.category.dto.CategoryDto;
import com.emotionbank.business.domain.transaction.constant.Visibility;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Enumerated(value = EnumType.STRING)
	private Visibility visibility;

	@OneToMany(mappedBy = "category")
	private List<Transaction> transactions;

	@Column(name = "category_name")
	private String categoryName;

	@Builder
	public Category(Long categoryId, User user, Visibility visibility,
		String categoryName) {
		this.categoryId = categoryId;
		this.user = user;
		this.visibility = visibility;
		this.categoryName = categoryName;
	}

	public static Category of(CategoryDto categoryDto, User user) {
		return Category.builder()
			.categoryName(categoryDto.getCategoryName())
			.user(user)
			.visibility(categoryDto.getVisibility())
			.build();
	}
}
