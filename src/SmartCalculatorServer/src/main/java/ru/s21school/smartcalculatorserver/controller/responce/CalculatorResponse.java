package ru.s21school.smartcalculatorserver.controller.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculatorResponse {
    private Status status;
    private String expression;
    private Double result;
}
