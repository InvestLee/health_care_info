package com.health_care_info.api.domain.token.service;


import com.health_care_info.api.domain.token.model.TokenDto;

public interface TokenService {

    public TokenDto issueAccessToken(Long userId);

    public TokenDto issueRefreshToken(Long userId);

    public Long validationToken(String token);
}
