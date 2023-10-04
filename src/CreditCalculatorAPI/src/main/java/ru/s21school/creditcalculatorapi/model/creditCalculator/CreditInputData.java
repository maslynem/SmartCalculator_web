package ru.s21school.creditcalculatorapi.model.creditCalculator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditInputData {
    private Double creditSum;
    private Double creditTerm;
    private Integer termCoefficient;
    private Double interestRate;
}
