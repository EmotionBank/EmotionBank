package com.emotionbank.business.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emotionbank.business.domain.user.entity.Follow;
import com.emotionbank.business.domain.user.entity.User;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

	Optional<Follow> findByFolloweeAndFollower(User followee, User follower);

}
