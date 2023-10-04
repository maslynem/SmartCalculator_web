package ru.s21school.smartcalculatorserver.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditRequest {
    private Double creditSum;
    private Double creditTerm;
    private Integer termCoefficient;
    private Double interestRate;
}
