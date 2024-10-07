package com.health_care_info.api.domain.pharmacy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyIdRequest {

    private String hpId; /*기관ID*/

    private String pageNo; /*페이지 번호*/

    private String numOfRows; /*목록 건수*/
}
