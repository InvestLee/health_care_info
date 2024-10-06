package com.health_care_info.api.domain.pharmacy.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacySearchRequest {

    private String Q0; /*주소(시도)*/

    private String Q1; /*주소(시군구)*/

    private String QT; /*월~일요일, 공휴일: 1~8*/

    private String QN; /*기관명*/

    private String ORD; /*순서*/

    private String pageNo; /*페이지 번호*/

    private String numOfRows; /*목록 건수*/
}
