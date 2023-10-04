package ru.s21school.calculatorapi.service.model.graphModel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.s21school.calculatorapi.service.model.calculator.Calculator;
import ru.s21school.calculatorapi.service.model.exceptions.WrongExpressionException;

@RequiredArgsConstructor
@Component
class GraphModel implements Graph {
    private final Calculator calculator;

    @Override
    public GraphData getGraphData(int minX, int maxX, String expression) {
        GraphData data = new GraphData();
        double h = (maxX - minX) / 10000.;
        for (double i = minX; i <= maxX; i += h) {
            try {
                double value = calculator.calculate(expression.replace("x", "(" + i + ")"));
                if (Double.isFinite(value)) {
                    data.getXValues().add(i);
                    data.getYValues().add(value);
                }
            } catch (WrongExpressionException wrongExpressionException) {
                throw wrongExpressionException;
            } catch (Exception ignored) {
                // ignoring exception to continue
            }
        }
        return data;
    }
}
