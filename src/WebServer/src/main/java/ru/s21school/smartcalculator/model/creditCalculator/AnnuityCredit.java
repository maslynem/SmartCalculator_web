package ru.s21school.smartcalculator.model.creditCalculator;

import lombok.Value;

@Value
public class AnnuityCredit {
    double allSum;
    double debt;
    double monthPay;
}
