package com.health_care_info.db.pharm;

import com.health_care_info.db.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "pharm_bm")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PharmEntity extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    private String hpId;
}
