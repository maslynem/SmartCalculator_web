package ru.s21school.creditcalculatorapi.model.creditCalculator;

import lombok.Value;

import java.util.List;

@Value
public class DifferentiatedCredit {
    double allSum;
    double debt;
    List<Double> monthPay;
}
