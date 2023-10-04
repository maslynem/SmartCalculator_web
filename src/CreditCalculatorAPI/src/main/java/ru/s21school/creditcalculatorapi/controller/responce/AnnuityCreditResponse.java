package ru.s21school.creditcalculatorapi.controller.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnuityCreditResponse {
    private Status status;
    private double allSum;
    private double debt;
    private double monthPay;
}
