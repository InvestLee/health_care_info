package com.health_care_info.api.domain.pharmacy.service;


import com.health_care_info.db.pharm.PharmEntity;

public interface PharmacyService {

    public PharmEntity registerBookmark(
            PharmEntity pharmEntity
    );
}
