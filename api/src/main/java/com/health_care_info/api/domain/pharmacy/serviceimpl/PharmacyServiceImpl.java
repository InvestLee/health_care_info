package com.health_care_info.api.domain.pharmacy.serviceimpl;


import com.health_care_info.api.common.error.CommonErrorCode;
import com.health_care_info.api.common.error.UserErrorCode;
import com.health_care_info.api.common.exception.ApiException;
import com.health_care_info.api.domain.pharmacy.service.PharmacyService;
import com.health_care_info.api.domain.user.service.UserService;
import com.health_care_info.db.user.UserEntity;
import com.health_care_info.db.user.UserRepository;
import com.health_care_info.db.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final UserRepository userRepository;

    public UserEntity search(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it ->{
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(()-> new ApiException(CommonErrorCode.NULL_POINT, "UserEntity Null"))
                ;
    }

    public UserEntity login(
            String email,
            String password
    ){
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public UserEntity getUserWithThrow(
            String email,
            String password
    ){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                email,
                password,
                UserStatus.REGISTERED
        ) .orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(
            Long userId
    ){
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(
                userId,
                UserStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
