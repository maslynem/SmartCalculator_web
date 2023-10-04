package ru.s21school.calculatorapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.s21school.calculatorapi.service.model.calculator.Calculator;

@Service
@Slf4j
@RequiredArgsConstructor
class CalculatorServiceImpl implements CalculatorService {
    private final Calculator calculator;

    @Override
    public double calculate(String expression) {
        return calculator.calculate(expression);
    }
}
