package com.emotionbank.business.domain.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.user.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByCategoryId(Long categoryId);

	List<Category> findByUser(User user);
}
