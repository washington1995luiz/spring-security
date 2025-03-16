package br.com.washington.springsecurity.services;

public interface RedisTokenService {
    String getActiveToken(String userId);

    void saveActiveToken(String userId, String token);

    void revokeToken(String userId);

    boolean isTokenRevoked(String userId, String token);
}
