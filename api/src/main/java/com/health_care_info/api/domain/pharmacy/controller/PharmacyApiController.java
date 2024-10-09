package com.health_care_info.api.domain.pharmacy.controller;

import com.health_care_info.api.common.annoation.UserSession;
import com.health_care_info.api.common.api.Api;
import com.health_care_info.api.domain.pharmacy.business.PharmacyBusiness;
import com.health_care_info.api.domain.pharmacy.model.PharmacyIdRequest;
import com.health_care_info.api.domain.pharmacy.model.PharmacyResponse;
import com.health_care_info.api.domain.pharmacy.model.PharmacySearchRequest;
import com.health_care_info.api.domain.user.model.User;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pharmacy")
public class PharmacyApiController {

    private final PharmacyBusiness pharmacyBusiness;

    @PostMapping("/register")
    public Api<PharmacyResponse> registerBookmark(
            @RequestBody Api<PharmacyIdRequest> request,

            @Parameter(hidden = true)
            @UserSession
            User user
    ) throws SAXException, IOException, ParserConfigurationException
    {
        var response = pharmacyBusiness.registerBookmark(
                user,
                request.getBody()
        );
        return Api.OK(response);
    }
}
