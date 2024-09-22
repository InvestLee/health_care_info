package com.health_care_info.api.domain.user.business;

import com.health_care_info.api.common.annoation.Business;
import com.health_care_info.api.domain.token.business.TokenBusiness;
import com.health_care_info.api.domain.token.model.TokenResponse;
import com.health_care_info.api.domain.user.converter.UserConverter;
import com.health_care_info.api.domain.user.service.UserService;
import com.health_care_info.api.domain.user.model.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * 가입처리
     * request -> entity
     * entity save
     * entity -> response
     */
    public UserResponse register(UserRegisterRequest request) {


        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;

        /*
        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "request Null"))
                ;

         */
    }

    /**
     * 로그인 처리
     * email, password를 통해 사용자 체크
     * user entity 로그인 확인
     * token 생성 및 response
     */
    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

    public UserResponse me(User user) {
        var userEntity = userService.getUserWithThrow(user.getId());
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
