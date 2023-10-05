package ru.s21school.creditcalculatorapi.model.annuityCreditCalculator;

import lombok.Value;

@Value
public class AnnuityCredit {
    double allSum;
    double debt;
    double monthPay;
}
