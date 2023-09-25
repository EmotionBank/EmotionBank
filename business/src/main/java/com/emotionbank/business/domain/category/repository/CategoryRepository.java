package com.emotionbank.business.domain.category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emotionbank.business.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByCategoryId(Long categoryId);

	@Query("SELECT c FROM Category c WHERE c.user.userId = :userId AND c.categoryName = :categoryName")
	Category findByUserIdAndCategoryName(Long userId, String categoryName);
}
