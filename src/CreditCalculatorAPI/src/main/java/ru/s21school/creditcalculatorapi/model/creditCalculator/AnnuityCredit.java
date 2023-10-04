package ru.s21school.creditcalculatorapi.model.creditCalculator;

import lombok.Value;

@Value
public class AnnuityCredit {
    double allSum;
    double debt;
    double monthPay;
}
