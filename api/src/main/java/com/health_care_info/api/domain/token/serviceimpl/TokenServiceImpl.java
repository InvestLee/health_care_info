package com.health_care_info.api.domain.token.serviceimpl;

import com.health_care_info.api.common.error.CommonErrorCode;
import com.health_care_info.api.common.exception.ApiException;
import com.health_care_info.api.domain.token.model.TokenDto;
import com.health_care_info.api.domain.token.service.TokenHelper;
import com.health_care_info.api.domain.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final TokenHelper tokenHelper;

    public TokenDto issueAccessToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelper.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelper.issueRefreshToken(data);
    }

    public Long validationToken(String token){
        var map = tokenHelper.validationTokenWithThrow(token);
        var userId = map.get("userId");
        Objects.requireNonNull(userId, ()->{throw new ApiException(CommonErrorCode.NULL_POINT);});
        return Long.parseLong(userId.toString());
    }
}
