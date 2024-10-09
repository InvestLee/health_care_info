package com.health_care_info.api.domain.pharmacy.service;


import com.health_care_info.db.pharm.PharmEntity;

import java.util.List;

public interface PharmacyService {

    public PharmEntity registerBookmark(
            PharmEntity pharmEntity
    );

    public List<PharmEntity> checkAllBookmark(
            Long userId
    );
}
