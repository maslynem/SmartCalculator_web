package ru.s21school.smartcalculatorapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HtmlController {

    @GetMapping
    public String getCalculatorPage() {
        return "index";
    }

    @GetMapping("/graph")
    public String getGraphsPage() {
        return "graph";
    }
}
