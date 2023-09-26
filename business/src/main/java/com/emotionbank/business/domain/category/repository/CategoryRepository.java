package com.emotionbank.business.domain.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.user.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByUserAndCategoryId(User user, Long categoryId);

	Optional<Category> findByUserAndCategoryName(User user, String categoryName);

	@Query("SELECT c FROM Category c WHERE c.user.userId = :userId AND c.categoryName = :categoryName")
	Category findByUserIdAndCategoryName(Long userId, String categoryName);

	List<Category> findByUser(User user);
}
