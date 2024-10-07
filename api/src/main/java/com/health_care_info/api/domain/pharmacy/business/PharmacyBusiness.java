package com.health_care_info.api.domain.pharmacy.business;

import com.health_care_info.api.common.annoation.Business;
import com.health_care_info.api.domain.pharmacy.converter.PharmacyConverter;
import com.health_care_info.api.domain.pharmacy.model.PharmacyIdRequest;
import com.health_care_info.api.domain.pharmacy.model.PharmacyResponse;
import com.health_care_info.api.domain.pharmacy.model.PharmacySearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Business
public class PharmacyBusiness {

    @Value("${pharmacy.serviceKey}")
    String pharmacyKey;

    private final PharmacyConverter pharmacyConverter;

    public List<PharmacyResponse> search(
            PharmacySearchRequest request
    ) throws SAXException, IOException, ParserConfigurationException
    {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + pharmacyKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode(request.getQ0(), "UTF-8")); /*주소(시도) ex : 서울특별시*/
        urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode(request.getQ1(), "UTF-8")); /*주소(시군구) ex : 강남구*/
        urlBuilder.append("&" + URLEncoder.encode("QT","UTF-8") + "=" + URLEncoder.encode(request.getQT(), "UTF-8")); /*월~일요일, 공휴일: 1~8*/
        urlBuilder.append("&" + URLEncoder.encode("QN","UTF-8") + "=" + URLEncoder.encode(request.getQN(), "UTF-8")); /*기관명 ex : 삼성약국*/
        urlBuilder.append("&" + URLEncoder.encode("ORD","UTF-8") + "=" + URLEncoder.encode(request.getORD(), "UTF-8")); /*순서 ex : NAME*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(request.getPageNo(), "UTF-8")); /*페이지 번호 ex : 1*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(request.getNumOfRows(), "UTF-8")); /*목록 건수 ex : 10*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
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
        List<PharmacyResponse> response = new ArrayList<>();
        PharmacyResponse pharmacyResponse;
        for(int i = 0; i < nodeList.getLength(); i++){
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;

            pharmacyResponse = pharmacyConverter.toResponse(eElement);

            response.add(pharmacyResponse);
        }

        return response;
    }

    public List<PharmacyResponse> search(
            PharmacyIdRequest request
    ) throws SAXException, IOException, ParserConfigurationException
    {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyBassInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + pharmacyKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("HPID","UTF-8") + "=" + URLEncoder.encode(request.getHpId(), "UTF-8")); /*기관ID ex : N0002117*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(request.getPageNo(), "UTF-8")); /*페이지 번호 ex : 1*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(request.getNumOfRows(), "UTF-8")); /*목록 건수 ex : 10*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
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
        List<PharmacyResponse> response = new ArrayList<>();
        PharmacyResponse pharmacyResponse;
        for(int i = 0; i < nodeList.getLength(); i++){
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;

            pharmacyResponse = pharmacyConverter.toResponse(eElement);

            response.add(pharmacyResponse);
        }

        return response;
    }
}
