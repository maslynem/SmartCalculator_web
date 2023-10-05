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
    private Integer creditSum;
    private Integer creditTerm;
    private Integer termCoefficient;
    private Double interestRate;
}
