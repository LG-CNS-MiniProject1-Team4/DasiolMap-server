package com.dasiolmapserver.dasiolmap.user.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public void save(String email, String refreshToken, long expirationMs) {
        redisTemplate.opsForValue().set(
                "RT:" + email,
                refreshToken,
                Duration.ofMillis(expirationMs));
    }

    public String findByUserId(String userId) {
        return redisTemplate.opsForValue().get("RT:" + userId);
    }

    public void delete(String email) {
        redisTemplate.delete("RT:" + email);
    }
}
