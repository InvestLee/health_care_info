package com.health_care_info.api.domain.pharmacy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyResponse {

    private String rnum;

    private String dutyAddr;

    private String dutyEtc;

    private String dutyMapimg;

    private String dutyName;

    private String dutyTel1;

    private String dutyTime1s;

    private String dutyTime2s;

    private String dutyTime3s;

    private String dutyTime4s;

    private String dutyTime5s;

    private String dutyTime6s;

    private String dutyTime7s;

    private String dutyTime8s;

    private String dutyTime1c;

    private String dutyTime2c;

    private String dutyTime3c;

    private String dutyTime4c;

    private String dutyTime5c;

    private String dutyTime6c;

    private String dutyTime7c;

    private String dutyTime8c;

    private String hpid;

    private String postCdn1;

    private String postCdn2;

    private String wgs84Lon;

    private String wgs84Lat;
}
