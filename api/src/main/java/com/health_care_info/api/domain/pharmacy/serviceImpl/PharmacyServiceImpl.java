package com.health_care_info.api.domain.pharmacy.serviceImpl;


import com.health_care_info.api.common.error.CommonErrorCode;
import com.health_care_info.api.common.error.UserErrorCode;
import com.health_care_info.api.common.exception.ApiException;
import com.health_care_info.api.domain.pharmacy.service.PharmacyService;
import com.health_care_info.api.domain.user.service.UserService;
import com.health_care_info.db.pharm.PharmEntity;
import com.health_care_info.db.pharm.PharmRepository;
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

    private final PharmRepository pharmRepository;

    public PharmEntity registerBookmark(PharmEntity pharmEntity){
        return Optional.ofNullable(pharmEntity)
                .map(it -> pharmRepository.save(pharmEntity))
                .orElseThrow(()-> new ApiException(CommonErrorCode.NULL_POINT, "UserEntity Null"))
                ;
    }
}
