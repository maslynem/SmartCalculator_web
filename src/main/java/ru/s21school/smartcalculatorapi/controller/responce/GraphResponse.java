package ru.s21school.smartcalculatorapi.controller.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GraphResponse {
    private Status status;
    private List<Double> xvalues;
    private List<Double> yvalues;
}
