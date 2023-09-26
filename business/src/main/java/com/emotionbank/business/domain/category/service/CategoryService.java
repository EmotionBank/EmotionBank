package com.emotionbank.business.domain.category.service;

import java.util.List;

import com.emotionbank.business.domain.category.dto.CategoryDto;

public interface CategoryService {
	void createCategory(CategoryDto categoryDto);

	List<CategoryDto> getCategoryList(Long userId);
}
