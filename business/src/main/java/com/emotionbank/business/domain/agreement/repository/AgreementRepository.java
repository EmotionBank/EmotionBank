package com.emotionbank.business.domain.agreement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emotionbank.business.domain.agreement.entity.Agreement;
import com.emotionbank.business.domain.terms.entity.Terms;
import com.emotionbank.business.domain.user.entity.User;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
	Optional<Agreement> findAgreementByUserAndTerms(User user, Terms terms);

	List<Agreement> findAllByUser(User user);
}
