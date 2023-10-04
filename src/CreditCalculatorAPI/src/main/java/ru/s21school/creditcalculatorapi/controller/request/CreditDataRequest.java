package ru.s21school.creditcalculatorapi.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditDataRequest {
    private Double creditSum;
    private Double creditTerm;
    private Integer termCoefficient;
    private Double interestRate;
}
