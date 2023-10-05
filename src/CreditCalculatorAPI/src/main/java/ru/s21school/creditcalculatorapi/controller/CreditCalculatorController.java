package ru.s21school.creditcalculatorapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.s21school.creditcalculatorapi.model.CreditInputData;
import ru.s21school.creditcalculatorapi.model.annuityCreditCalculator.AnnuityCredit;
import ru.s21school.creditcalculatorapi.model.annuityCreditCalculator.AnnuityCreditCalculator;
import ru.s21school.creditcalculatorapi.model.differentiatedCreditCalculator.DifferentiatedCredit;
import ru.s21school.creditcalculatorapi.model.differentiatedCreditCalculator.DifferentiatedCreditCalculator;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/credit-calculator/")
@RequiredArgsConstructor
public class CreditCalculatorController {

    private final AnnuityCreditCalculator annuityCreditCalculator;
    private final DifferentiatedCreditCalculator differentiatedCreditCalculator;

    @PostMapping("ann")
    AnnuityCredit calculateAnnuityCredit(@RequestBody CreditInputData creditInputData) {
        log.info("POST /api/v1/credit-calculator/ann");
        log.info("Credit Input Data: {}", creditInputData);
        return annuityCreditCalculator.countAnnuityCredit(creditInputData);
    }

    @PostMapping("diff")
    DifferentiatedCredit calculateDifferentiatedCredit(@RequestBody CreditInputData creditInputData) {
        log.info("POST /api/v1/credit-calculator/diff");
        log.info("Credit Input Data: {}", creditInputData);
        return differentiatedCreditCalculator.countDifferentiatedCredit(creditInputData);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleException(Exception exception) throws JsonProcessingException {
        log.warn(exception.getMessage());
        return ResponseEntity.badRequest().body(new ObjectMapper().writeValueAsString(Map.of("message", exception.getMessage())));
    }

}
