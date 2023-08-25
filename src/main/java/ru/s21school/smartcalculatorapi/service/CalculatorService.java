package ru.s21school.smartcalculatorapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.s21school.smartcalculatorapi.model.calculator.Calculator;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculatorService {
    public final Calculator calculator;

    public double calculate(String expression) {
        double result = calculator.calculate(expression);
        //todo save to db
        return result;
    }
}
