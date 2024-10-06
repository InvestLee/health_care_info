package com.health_care_info.api.domain.pharmacy.controller;

import com.health_care_info.api.common.api.Api;
import com.health_care_info.api.domain.pharmacy.converter.PharmacyConverter;
import com.health_care_info.api.domain.pharmacy.model.PharmacyResponse;
import com.health_care_info.api.domain.pharmacy.model.PharmacySearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/pharmacy")
public class PharmacyOpenApiController {

    @Value("${pharmacy.serviceKey}")
    String pharmacyKey;
    PharmacyConverter pharmacyConverter;

    @PostMapping("/pharmacyList")
    public Api<List<PharmacyResponse>> pharmacyList(
            @RequestBody Api<PharmacySearchRequest> request
    ) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + pharmacyKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode(request.getBody().getQ0(), "UTF-8")); /*주소(시도) ex : 서울특별시*/
        urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode(request.getBody().getQ1(), "UTF-8")); /*주소(시군구) ex : 강남구*/
        urlBuilder.append("&" + URLEncoder.encode("QT","UTF-8") + "=" + URLEncoder.encode(request.getBody().getQT(), "UTF-8")); /*월~일요일, 공휴일: 1~8*/
        urlBuilder.append("&" + URLEncoder.encode("QN","UTF-8") + "=" + URLEncoder.encode(request.getBody().getQN(), "UTF-8")); /*기관명 ex : 삼성약국*/
        urlBuilder.append("&" + URLEncoder.encode("ORD","UTF-8") + "=" + URLEncoder.encode(request.getBody().getORD(), "UTF-8")); /*순서 ex : NAME*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(request.getBody().getPageNo(), "UTF-8")); /*페이지 번호 ex : 1*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(request.getBody().getNumOfRows(), "UTF-8")); /*목록 건수 ex : 10*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();


        InputSource is = new InputSource(new StringReader(sb.toString()));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("items").item(0).getChildNodes();
        System.out.println(nodeList);
        List<PharmacyResponse> list = new ArrayList<>();
        PharmacyResponse pharmacyResponse = new PharmacyResponse();
        for(int i = 0; i < nodeList.getLength(); i++){
            Map<String, String> map = new HashMap<>();
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;


            pharmacyConverter = new PharmacyConverter();
            pharmacyResponse = pharmacyConverter.toResponse(eElement);

            list.add(pharmacyResponse);
            System.out.println(pharmacyResponse);
        }

        return Api.OK(list);
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
