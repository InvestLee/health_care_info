package com.health_care_info.api.domain.pharmacy.controller;

import com.health_care_info.api.common.api.Api;
import com.health_care_info.api.domain.pharmacy.business.PharmacyBusiness;
import com.health_care_info.api.domain.pharmacy.model.PharmacyIdRequest;
import com.health_care_info.api.domain.pharmacy.model.PharmacyResponse;
import com.health_care_info.api.domain.pharmacy.model.PharmacySearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/pharmacy")
public class PharmacyOpenApiController {

    private final PharmacyBusiness pharmacyBusiness;

    @PostMapping("/pharmacyList")
    public Api<List<PharmacyResponse>> listSearch(
            @RequestBody Api<PharmacySearchRequest> request
    ) throws SAXException, IOException, ParserConfigurationException
    {
        var response = pharmacyBusiness.search(request.getBody());
        return Api.OK(response);
    }

    @PostMapping("/pharmacyId")
    public Api<PharmacyResponse> hpIdSearch(
            @RequestBody Api<PharmacyIdRequest> request
    ) throws SAXException, IOException, ParserConfigurationException
    {
        var response = pharmacyBusiness.search(request.getBody());
        return Api.OK(response);
    }
}
