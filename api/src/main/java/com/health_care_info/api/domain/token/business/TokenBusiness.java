package com.health_care_info.api.domain.token.business;

import com.health_care_info.api.common.annoation.Business;
import com.health_care_info.api.common.error.CommonErrorCode;
import com.health_care_info.api.common.exception.ApiException;
import com.health_care_info.api.domain.token.converter.TokenConverter;
import com.health_care_info.api.domain.token.model.TokenResponse;
import com.health_care_info.api.domain.token.service.TokenService;
import com.health_care_info.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /**
     * user entity user Id 추출
     * access, refresh token 발행
     * converter -> token response로 변경
     */
    public TokenResponse issueToken(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
                .map(ue -> {
                    return ue.getId();
                })
                .map(userId -> {
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(()-> new ApiException(CommonErrorCode.NULL_POINT));
    }

    public Long validationAccessToken(String accessToken){
        var userId = tokenService.validationToken(accessToken);
        return userId;
    }
}
