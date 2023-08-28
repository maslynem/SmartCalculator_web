package ru.s21school.smartcalculatorapi.controller.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculatorErrorResponse {
    private Status status;
    private String message;
}
