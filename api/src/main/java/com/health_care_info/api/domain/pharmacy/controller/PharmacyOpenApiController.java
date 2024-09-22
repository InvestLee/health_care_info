package com.health_care_info.api.domain.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/pharmacyList")
    public String pharmacyList() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + pharmacyKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode("서울특별시", "UTF-8")); /*주소(시도)*/
        urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode("강남구", "UTF-8")); /*주소(시군구)*/
        urlBuilder.append("&" + URLEncoder.encode("QT","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*월~일요일, 공휴일: 1~8*/
        urlBuilder.append("&" + URLEncoder.encode("QN","UTF-8") + "=" + URLEncoder.encode("삼성약국", "UTF-8")); /*기관명*/
        urlBuilder.append("&" + URLEncoder.encode("ORD","UTF-8") + "=" + URLEncoder.encode("NAME", "UTF-8")); /*순서*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*목록 건수*/
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
        List<Map<String, String>> list = new ArrayList<>();
        for(int i = 0; i < nodeList.getLength(); i++){
            Map<String, String> map = new HashMap<>();
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;

            map.put("rnum", getTagValue("rnum", eElement)); //item 번호
            map.put("dutyAddr", getTagValue("dutyAddr", eElement)); //주소
            map.put("dutyEtc", getTagValue("dutyEtc", eElement)); //비고
            map.put("dutyMapimg", getTagValue("dutyMapimg", eElement)); //간이약도
            map.put("dutyName", getTagValue("dutyName", eElement)); //기관명
            map.put("dutyTel1", getTagValue("dutyTel1", eElement)); //대표전화
            map.put("dutyTime1s", getTagValue("dutyTime1s", eElement)); //시작 진료시간(월)
            map.put("dutyTime2s", getTagValue("dutyTime2s", eElement)); //시작 진료시간(화)
            map.put("dutyTime3s", getTagValue("dutyTime3s", eElement)); //시작 진료시간(수)
            map.put("dutyTime4s", getTagValue("dutyTime4s", eElement)); //시작 진료시간(목)
            map.put("dutyTime5s", getTagValue("dutyTime5s", eElement)); //시작 진료시간(금)
            map.put("dutyTime6s", getTagValue("dutyTime6s", eElement)); //시작 진료시간(토)
            map.put("dutyTime7s", getTagValue("dutyTime7s", eElement)); //시작 진료시간(일)
            map.put("dutyTime8s", getTagValue("dutyTime8s", eElement)); //시작 진료시간(공휴일)
            map.put("dutyTime1c", getTagValue("dutyTime1c", eElement)); //종료 진료시간(월)
            map.put("dutyTime2c", getTagValue("dutyTime2c", eElement)); //종료 진료시간(화)
            map.put("dutyTime3c", getTagValue("dutyTime3c", eElement)); //종료 진료시간(수)
            map.put("dutyTime4c", getTagValue("dutyTime4c", eElement)); //종료 진료시간(목)
            map.put("dutyTime5c", getTagValue("dutyTime5c", eElement)); //종료 진료시간(금)
            map.put("dutyTime6c", getTagValue("dutyTime6c", eElement)); //종료 진료시간(토)
            map.put("dutyTime7c", getTagValue("dutyTime7c", eElement)); //종료 진료시간(일)
            map.put("dutyTime8c", getTagValue("dutyTime8c", eElement)); //종료 진료시간(공휴일)
            map.put("hpid", getTagValue("hpid", eElement)); //기관ID
            map.put("postCdn1", getTagValue("postCdn1", eElement)); //우편번호1
            map.put("postCdn2", getTagValue("postCdn2", eElement)); //우편번호2
            map.put("wgs84Lon", getTagValue("wgs84Lon", eElement)); //병원경도
            map.put("wgs84Lat", getTagValue("wgs84Lat", eElement)); //병원위도

            list.add(map);
            System.out.println(map);
        }

        return sb.toString();
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
