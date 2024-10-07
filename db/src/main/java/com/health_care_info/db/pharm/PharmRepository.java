package com.health_care_info.db.pharm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmRepository extends JpaRepository<PharmEntity, Long> {

    Optional<PharmEntity> findFirstByIdOrderByIdDesc(Long userId);

    Optional<PharmEntity> findFirstByEmailAndHpIdOrderByIdDesc(String email, String hpId);
}
