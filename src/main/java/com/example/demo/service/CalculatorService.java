package com.example.demo.service;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CalculatorService {

    public JSONObject convertXmlToJson(String xml) {
        return XML.toJSONObject(xml);
    }

    public JSONObject getJsonResponse() {
        String xmlResponse = callSoapService();
        return convertXmlToJson(xmlResponse);
    }

    public String callSoapService() {
        String url = "http://www.dneonline.com/calculator.asmx";

        String xmlRequest = """
        <?xml version="1.0" encoding="utf-8"?>
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
                       xmlns:tem="http://tempuri.org/">
           <soap:Body>
              <tem:Add>
                 <tem:intA>10</tem:intA>
                 <tem:intB>5</tem:intB>
              </tem:Add>
           </soap:Body>
        </soap:Envelope>
        """;

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", "http://tempuri.org/Add");
        headers.add("Accept", "text/xml");
        headers.add("User-Agent", "Mozilla/5.0");

        HttpEntity<String> request = new HttpEntity<>(xmlRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.getBody(); // XML response
    }
}
