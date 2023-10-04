package ru.s21school.smartcalculatorserver.controller.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GraphResponse {
    private Status status;
    private List<Double> xValues;
    private List<Double> yValues;
}
