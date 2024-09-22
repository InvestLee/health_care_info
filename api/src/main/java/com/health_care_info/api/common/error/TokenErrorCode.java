package com.health_care_info.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCode{

    INVALID_TOKEN(400, 2400, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400, 2401, "만료된 토큰"),
    TOKEN_EXCEPTION(400, 2402, "알수없는 토큰 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2403, "인증 헤더 토큰 없음")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
