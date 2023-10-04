package ru.s21school.creditcalculatorapi.controller.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DifferentiatedCreditResponse {
    private Status status;
    private double allSum;
    private double debt;
    private List<Double> monthPay;
}
