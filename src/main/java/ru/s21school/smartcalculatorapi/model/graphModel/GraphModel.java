package ru.s21school.smartcalculatorapi.model.graphModel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.s21school.smartcalculatorapi.model.calculator.Calculator;
import ru.s21school.smartcalculatorapi.model.utils.Pair;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GraphModel {
    private final Calculator calculator;

    public List<Pair<Double, Double>> getGraphData(int minX, int maxX, String expression) {
         List<Pair<Double, Double>> data = new ArrayList<>();
        double h = (maxX - minX) / 10000.;
        for (double i = minX; i <= maxX; i += h) {
            try {
                double value = calculator.calculate(expression.replace("x", "(" + i + ")"));
                if (Double.isFinite(value)) {
                    data.add(Pair.of(i, value));
                }
            } catch (Exception ignored) {
                // ignoring exception to continue
            }
        }
        return data;
    }
}
