package com.health_care_info.api.domain.pharmacy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyIdRequest {

    private String hpId; /*기관ID*/
}
