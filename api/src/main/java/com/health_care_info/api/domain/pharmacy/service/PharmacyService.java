package com.health_care_info.api.domain.pharmacy.service;


import com.health_care_info.db.user.UserEntity;

public interface PharmacyService {

    public UserEntity search(
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
