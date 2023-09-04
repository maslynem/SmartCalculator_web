package ru.s21school.calculatorapi.model.graphModel;

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
    private List<Double> xvalues = new ArrayList<>();
    private List<Double> yvalues = new ArrayList<>();
}
