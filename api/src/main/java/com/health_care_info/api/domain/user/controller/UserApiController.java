package com.health_care_info.api.domain.user.controller;


import com.health_care_info.api.common.annoation.UserSession;
import com.health_care_info.api.common.api.Api;
import com.health_care_info.api.domain.user.business.UserBusiness;
import com.health_care_info.api.domain.user.model.User;
import com.health_care_info.api.domain.user.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession User user
    ){

        var response = userBusiness.me(user);
        return Api.OK(response);
    }
}
