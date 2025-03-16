package br.com.washington.springsecurity.services.impl;

import br.com.washington.springsecurity.services.RedisTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RedisTokenServiceImpl implements RedisTokenService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${redis-variable.whitelist}")
    private String whitelist;

    @Value("${redis-variable.blacklist}")
    private String blacklist;

    @Value("${redis-variable.time-to-expires}")
    private long duration;

    @Override
    public String getActiveToken(String userId) {
        return redisTemplate.opsForValue().get(whitelist.concat(userId));
    }

    @Override
    public void saveActiveToken(String userId, String token) {
        redisTemplate.opsForValue().set(whitelist.concat(userId), token, Duration.ofSeconds(duration));
    }

    @Override
    public void revokeToken(String userId) {
        String token = this.getActiveToken(userId);
        redisTemplate.opsForValue().set(blacklist.concat(userId), token, Duration.ofSeconds(duration));
    }

    @Override
    public boolean isTokenRevoked(String userId, String token) {
        return Objects.equals(redisTemplate.opsForValue().get(blacklist.concat(userId)), token);
    }
}
