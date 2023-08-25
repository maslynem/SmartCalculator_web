package ru.s21school.smartcalculatorapi.model.graphModel;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.s21school.smartcalculatorapi.model.utils.Pair;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class GraphModelTest {
    private final GraphModel graphModel;

    @Test
    void GraphModel_GetGraphDataFromSinX_SizeShouldBe10000() {
        List<Pair<Double, Double>> graphData = graphModel.getGraphData(-30, 30, "sin(x)");
        Assertions.assertEquals(10000, graphData.size());
    }

    @Test
    void GraphModel_GetGraphDataFromSqrtX_SizeShouldBe5000() {
        List<Pair<Double, Double>> graphData = graphModel.getGraphData(-30, 30, "sqrt(x)");
        Assertions.assertEquals(5000, graphData.size());
    }

    @Test
    void GraphModel_GetGraphDataFromIncorrectData_SizeShouldBe0() {
        List<Pair<Double, Double>> graphData = graphModel.getGraphData(-30, 30, "ERROR");
        Assertions.assertEquals(0, graphData.size());
    }
}