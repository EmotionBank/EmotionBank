package com.emotionbank.business.domain.category.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.category.dto.CategoryDto;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.category.repository.CategoryRepository;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	@Override
	@Transactional
	public CategoryDto createCategory(CategoryDto categoryDto) {
		User user = userRepository.findById(categoryDto.getUserId())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		Category category = categoryRepository.save(Category.of(categoryDto, user));
		return CategoryDto.from(category);
	}
}
