package com.emotionbank.business.domain.category.service;

import java.util.List;

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
		Category category = categoryRepository.findByCategoryId(categoryId)
			.orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_EXIST));

		// user id 비교
		if (!userId.equals(category.getUser().getUserId())) {
			throw new BusinessException(ErrorCode.USER_NOT_EQUAL);
		}

		//  기본 카테고리는 삭제 불가능
		if ("기본".equals(category.getCategoryName())) {
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
}
