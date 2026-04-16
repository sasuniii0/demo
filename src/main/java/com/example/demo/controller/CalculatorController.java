package com.example.demo.controller;

import com.example.demo.dto.ApiResponseDTO;
import com.example.demo.service.CalculatorService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalculatorController {
    private final CalculatorService service;

    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public ApiResponseDTO<?> getAddResult() {

        try {
            JSONObject json = service.getJsonResponse();

            int result = json
                    .getJSONObject("soap:Envelope")
                    .getJSONObject("soap:Body")
                    .getJSONObject("AddResponse")
                    .getInt("AddResult");

            return new ApiResponseDTO<>(
                    "0000",
                    "SUCCESS",
                    "",
                    Map.of("result", result)
            );

        } catch (Exception e) {

            return new ApiResponseDTO<>(
                    "9999",
                    "ERROR",
                    e.getMessage(),
                    null
            );
        }
    }
}
