package com.emotionbank.business.domain.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emotionbank.business.domain.user.constant.SocialType;
import com.emotionbank.business.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByNicknameContains(String nickname);

	Optional<User> findBySocialIdAndSocialType(String socialId, SocialType socialType);

	boolean existsByNickname(String nickname);
}
