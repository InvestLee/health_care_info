package com.health_care_info.db.pharm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PharmRepository extends JpaRepository<PharmEntity, Long> {

    List<PharmEntity> findAllByUserIdOrderByIdDesc(Long userId);

    Optional<PharmEntity> findFirstByUserIdAndHpId(Long userId, String hpId);
}
