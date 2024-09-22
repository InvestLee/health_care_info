package com.health_care_info.api.domain.token.converter;


import com.health_care_info.api.common.annoation.Converter;
import com.health_care_info.api.common.error.CommonErrorCode;
import com.health_care_info.api.common.exception.ApiException;
import com.health_care_info.api.domain.token.model.TokenDto;
import com.health_care_info.api.domain.token.model.TokenResponse;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ){
        Objects.requireNonNull(accessToken, ()->{throw new ApiException(CommonErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken, ()->{throw new ApiException(CommonErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
