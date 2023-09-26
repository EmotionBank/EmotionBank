package com.emotionbank.business.domain.terms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emotionbank.business.domain.terms.entity.Terms;

public interface TermsRepository extends JpaRepository<Terms, Long> {
}
