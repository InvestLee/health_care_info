package com.health_care_info.api.domain.user.service;


import com.health_care_info.db.user.UserEntity;

public interface UserService {

    public UserEntity register(
            UserEntity userEntity
    );

    public UserEntity login(
            String email,
            String password
    );

    public UserEntity getUserWithThrow(
            String email,
            String password
    );

    public UserEntity getUserWithThrow(
            Long userId
    );
}
