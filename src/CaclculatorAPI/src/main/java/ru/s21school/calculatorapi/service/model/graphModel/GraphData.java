package ru.s21school.calculatorapi.service.model.graphModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraphData {
    @Builder.Default
    private List<Double> xValues = new ArrayList<>();
    @Builder.Default
    private List<Double> yValues = new ArrayList<>();
}
