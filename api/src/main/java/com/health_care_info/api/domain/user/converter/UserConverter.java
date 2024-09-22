package com.health_care_info.api.domain.user.converter;


import com.health_care_info.api.common.annoation.Converter;
import com.health_care_info.api.common.error.CommonErrorCode;
import com.health_care_info.api.common.exception.ApiException;
import com.health_care_info.api.domain.user.model.UserRegisterRequest;
import com.health_care_info.api.domain.user.model.UserResponse;
import com.health_care_info.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request){

        return Optional.ofNullable(request)
                .map(it ->{
                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(()-> new ApiException(CommonErrorCode.NULL_POINT, "UserRegisterRequest Null"))
                ;
    }

    public UserResponse toResponse(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(it ->{
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .status(userEntity.getStatus())
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                })
                .orElseThrow(()-> new ApiException(CommonErrorCode.NULL_POINT, "UserEntity Null"))
                ;
    }
}
