package ru.s21school.smartcalculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.s21school.smartcalculator.model.creditCalculator.CreditCalculator;
import ru.s21school.smartcalculator.model.creditCalculator.CreditInputData;
import ru.s21school.smartcalculator.model.creditCalculator.CreditType;
import ru.s21school.smartcalculator.validator.CreditInputValidator;

@Slf4j
@Controller
@RequestMapping("/credit-calculator")
@RequiredArgsConstructor
public class CreditController {
    private final CreditInputValidator creditInputValidator;
    private final CreditCalculator creditCalculator;


    @GetMapping()
    public String getCreditCalcPage(Model model) {
        log.info("GET /credit-calculator");
        model.addAttribute("creditData", CreditInputData.builder().creditType(CreditType.ANNUITY).build());
        return "credit-calculator";
    }

    @PostMapping()
    public String calcCredit(@ModelAttribute("creditData") CreditInputData creditInputData, BindingResult bindingResult, Model model) {
       creditInputValidator.validate(creditInputData, bindingResult);
        if (bindingResult.hasErrors()) {
           log.warn("POST /credit-calculator credit data has errors: {}", bindingResult.getAllErrors());
           return "credit-calculator";
       }
       if (creditInputData.getCreditType().equals(CreditType.ANNUITY)) {
           model.addAttribute("annuityCredit", creditCalculator.countAnnuityCredit(creditInputData));
       } else {
           model.addAttribute("differentiatedCredit", creditCalculator.countDifferentiatedCredit(creditInputData));
       }
       return "credit-calculator";
    }

}
