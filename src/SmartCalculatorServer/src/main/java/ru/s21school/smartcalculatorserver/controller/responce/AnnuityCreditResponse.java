package ru.s21school.smartcalculatorserver.controller.responce;

import lombok.Value;

@Value
public class AnnuityCreditResponse {
    double allSum;
    double debt;
    double monthPay;
}
