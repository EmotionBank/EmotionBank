package com.emotionbank.business.domain.category.service;

import static com.emotionbank.business.global.error.ErrorCode.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.category.dto.CategoryDto;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.category.repository.CategoryRepository;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.transaction.repository.TransactionRepository;
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
	private final TransactionRepository transactionRepository;

	private static final String basicCategoryName = "기본";

	@Override
	@Transactional
	public void createCategory(CategoryDto categoryDto) {
		User user = userRepository.findById(categoryDto.getUserId())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		categoryRepository.save(Category.of(categoryDto, user));
	}

	@Override
	@Transactional
	public void deleteCategory(Long categoryId, Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

		Category category = categoryRepository.findByUserAndCategoryId(user, categoryId)
			.orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_EXIST));

		//  기본 카테고리는 삭제 불가능
		if (basicCategoryName.equals(category.getCategoryName())) {
			throw new BusinessException(ErrorCode.BASIC_CATEGORY);
		}

		// 기본 카테고리
		Category basicCategory = categoryRepository.findByUserIdAndCategoryName(userId, "기본");

		// 기존 거래 내역 값 변경
		List<Transaction> transactions = transactionRepository.findByCategory(category);
		for (Transaction transaction : transactions) {
			transaction.updateCategory(basicCategory);
		}

		categoryRepository.delete(category);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoryDto> getCategoryList(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		List<Category> categoryList = categoryRepository.findByUser(user);

		List<CategoryDto> categoryDtoList = categoryList.stream()
			.map(CategoryDto::from)
			.collect(Collectors.toList());
		return categoryDtoList;
	}

	@Override
	@Transactional
	public void updateCategory(CategoryDto categoryDto) {
		User user = userRepository.findById(categoryDto.getUserId())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		Category category = categoryRepository.findByUserAndCategoryId(user, categoryDto.getCategoryId())
			.orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_EXIST));

		if (basicCategoryName.equals(category.getCategoryName())) {
			throw new BusinessException(ErrorCode.BASIC_CATEGORY);
		}

		category.updateCategory(categoryDto.getCategoryName(), categoryDto.getVisibility());

	}
}
