package com.health_care_info.api.domain.pharmacy.converter;


import com.health_care_info.api.common.annoation.Converter;
import com.health_care_info.api.common.error.CommonErrorCode;
import com.health_care_info.api.common.exception.ApiException;
import com.health_care_info.api.domain.pharmacy.model.PharmacyResponse;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class PharmacyConverter {

    public PharmacyResponse toResponse(Element eElement) {

        return Optional.ofNullable(eElement)
                .map(it ->{
                    return PharmacyResponse.builder()
                            .dutyUrl(getTagValue("dutyUrl", eElement))
                            .dutyImg(getTagValue("dutyImg", eElement))
                            .dutyInf(getTagValue("dutyInf", eElement))
                            .rnum(getTagValue("rnum", eElement))
                            .dutyAddr(getTagValue("dutyAddr", eElement))
                            .dutyEtc(getTagValue("dutyEtc", eElement))
                            .dutyMapimg(getTagValue("dutyMapimg", eElement))
                            .dutyName(getTagValue("dutyName", eElement))
                            .dutyTel1(getTagValue("dutyTel1", eElement))
                            .dutyTime1s(getTagValue("dutyTime1s", eElement))
                            .dutyTime1c(getTagValue("dutyTime1c", eElement))
                            .dutyTime2s(getTagValue("dutyTime2s", eElement))
                            .dutyTime2c(getTagValue("dutyTime2c", eElement))
                            .dutyTime3s(getTagValue("dutyTime3s", eElement))
                            .dutyTime3c(getTagValue("dutyTime3c", eElement))
                            .dutyTime4s(getTagValue("dutyTime4s", eElement))
                            .dutyTime4c(getTagValue("dutyTime4c", eElement))
                            .dutyTime5s(getTagValue("dutyTime5s", eElement))
                            .dutyTime5c(getTagValue("dutyTime5c", eElement))
                            .dutyTime6s(getTagValue("dutyTime6s", eElement))
                            .dutyTime6c(getTagValue("dutyTime6c", eElement))
                            .dutyTime7s(getTagValue("dutyTime7s", eElement))
                            .dutyTime7c(getTagValue("dutyTime7c", eElement))
                            .dutyTime8s(getTagValue("dutyTime8s", eElement))
                            .dutyTime8c(getTagValue("dutyTime8c", eElement))
                            .hpid(getTagValue("hpid", eElement))
                            .postCdn1(getTagValue("postCdn1", eElement))
                            .postCdn2(getTagValue("postCdn2", eElement))
                            .wgs84Lon(getTagValue("wgs84Lon", eElement))
                            .wgs84Lat(getTagValue("wgs84Lat", eElement))
                            .build();
                })
                .orElseThrow(()-> new ApiException(CommonErrorCode.NULL_POINT, "UserEntity Null"))
                ;
    }

    private static String getTagValue(String tag, Element eElement) {
        NodeList nList = null;
        Node nValue = null;
        try {
            nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
            nValue = nList.item(0);
        } catch (NullPointerException e){
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (nValue == null)
            return "";
        return nValue.getNodeValue();
    }
}
