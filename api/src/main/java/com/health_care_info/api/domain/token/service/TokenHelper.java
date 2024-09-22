package com.health_care_info.api.domain.token.service;


import com.health_care_info.api.domain.token.model.TokenDto;

import java.util.Map;

public interface TokenHelper {

    TokenDto issueAccessToken(Map<String, Object> data);
    TokenDto issueRefreshToken(Map<String, Object> data);
    Map<String, Object> validationTokenWithThrow(String token);
}
