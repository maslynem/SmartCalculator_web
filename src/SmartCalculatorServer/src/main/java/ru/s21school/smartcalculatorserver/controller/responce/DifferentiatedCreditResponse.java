package ru.s21school.smartcalculatorserver.controller.responce;

import lombok.Value;

import java.util.List;

@Value
public class DifferentiatedCreditResponse {
    double allSum;
    double debt;
    List<Double> monthPay;
}
