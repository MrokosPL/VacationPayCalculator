package com.mrokos.VacationPay.controllers;

import com.mrokos.VacationPay.dtos.VacationPayDto;
import com.mrokos.VacationPay.services.PayCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayCalculatorController {
    private final PayCalculatorService payCalculatorService;

    public PayCalculatorController(PayCalculatorService payCalculatorService) {
        this.payCalculatorService = payCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody VacationPayDto vacationPay){
        return ResponseEntity.ok(payCalculatorService.calculate(vacationPay));
    }
}
