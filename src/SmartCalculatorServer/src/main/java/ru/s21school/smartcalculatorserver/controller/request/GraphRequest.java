package ru.s21school.smartcalculatorserver.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GraphRequest {
    private Integer minX;
    private Integer maxX;
    private String expression;
}
