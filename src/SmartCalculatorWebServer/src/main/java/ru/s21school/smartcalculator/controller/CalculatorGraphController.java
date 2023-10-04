package ru.s21school.smartcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CalculatorGraphController {

    @GetMapping
    public String getCalculatorPage() {
        return "calculator";
    }

    @GetMapping("/graph")
    public String getGraphsPage() {
        return "graph";
    }
}
