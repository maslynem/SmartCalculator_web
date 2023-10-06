package ru.s21school.creditcalculatorapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditInputData {
    private double creditSum;
    private int creditTerm;
    private int termCoefficient;
    private double interestRate;
}
