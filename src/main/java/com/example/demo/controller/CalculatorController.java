package com.example.demo.controller;

import com.example.demo.service.CalculatorService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculatorController {
    private final CalculatorService service;

    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    @GetMapping("/add-full")
    public String getFullResponse() {
        JSONObject json = service.getJsonResponse();
        return json.toString(2);
    }

    @GetMapping("/add")
    public int getAddResult() {
        JSONObject json = service.getJsonResponse();

        return json
                .getJSONObject("soap:Envelope")
                .getJSONObject("soap:Body")
                .getJSONObject("AddResponse")
                .getInt("AddResult");
    }
}
