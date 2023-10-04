package ru.s21school.smartcalculatorserver.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculatorRequest {
    private String userUUID;
    private String expression;
}
