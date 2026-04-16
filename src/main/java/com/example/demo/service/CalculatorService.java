package com.example.demo.service;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class CalculatorService {

    private final WebClient webClient;

    public CalculatorService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://www.dneonline.com/calculator.asmx")
                .defaultHeader("Content-Type", "text/xml")
                .defaultHeader("SOAPAction", "http://tempuri.org/Add")
                .defaultHeader("Accept", "text/xml")
                .build();
    }

    public JSONObject getJsonResponse() {
        try {
            String xmlResponse = callSoapService();
            return XML.toJSONObject(xmlResponse);

        } catch (Exception e) {
            throw new RuntimeException("SOAP Service Error: " + e.getMessage());
        }
    }

    public String callSoapService() {

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

        return webClient.post()
                .bodyValue(xmlRequest)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorResume(ex -> {
                    throw new RuntimeException("External SOAP API failed");
                })
                .block();
    }
}
