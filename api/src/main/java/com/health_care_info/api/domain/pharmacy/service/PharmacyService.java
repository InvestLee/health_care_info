package com.health_care_info.api.domain.pharmacy.service;


import com.health_care_info.db.pharm.PharmEntity;
import com.health_care_info.db.user.UserEntity;

public interface PharmacyService {

    public PharmEntity registerBookmark(
            PharmEntity pharmEntity
    );
}
