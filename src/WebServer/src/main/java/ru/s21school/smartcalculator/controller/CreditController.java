package ru.s21school.smartcalculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/credit-calculator")
@RequiredArgsConstructor
public class CreditController {
    @GetMapping()
    public String getCreditCalcPage(Model model) {
        log.info("GET /credit-calculator");
        return "credit-calculator";
    }
}
