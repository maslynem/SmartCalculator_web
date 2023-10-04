package ru.s21school.creditcalculatorapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.s21school.creditcalculatorapi.model.creditCalculator.*;

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

}
