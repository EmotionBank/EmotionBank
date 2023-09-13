package com.emotionbank.business.domain.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emotionbank.business.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	Optional<List<User>> findByNicknameContains(String nickname);
}
