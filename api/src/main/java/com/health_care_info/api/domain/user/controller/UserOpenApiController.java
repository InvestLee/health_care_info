package com.health_care_info.api.domain.user.controller;

import com.health_care_info.api.common.api.Api;
import com.health_care_info.api.domain.user.business.UserBusiness;
import com.health_care_info.api.domain.token.model.TokenResponse;
import com.health_care_info.api.domain.user.model.UserLoginRequest;
import com.health_care_info.api.domain.user.model.UserRegisterRequest;
import com.health_care_info.api.domain.user.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping("/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody Api<UserRegisterRequest> request
    ){
        var response = userBusiness.register(request.getBody());
        return Api.OK(response);
    }

    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody Api<UserLoginRequest> request
    ){
        var response = userBusiness.login(request.getBody());
        return Api.OK(response);
    }
}
