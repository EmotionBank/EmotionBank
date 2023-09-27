package com.emotionbank.business.domain.auth.repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.emotionbank.business.domain.auth.entity.RefreshToken;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
	private final RedisTemplate<String, String> redisTemplate;

	public void save(RefreshToken refreshToken) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(String.valueOf(refreshToken.getUserId()), refreshToken.getRefreshToken(), 14,
			TimeUnit.DAYS);
	}

	public Optional<RefreshToken> findRefreshTokenByUserId(Long userId) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String refreshToken = valueOperations.get(String.valueOf(userId));

		if (refreshToken == null) {
			return Optional.empty();
		}
		return Optional.of(RefreshToken.of(userId, refreshToken));
	}

}
