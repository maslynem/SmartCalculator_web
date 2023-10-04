package ru.s21school.calculatorapi.service.model.graphModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GraphModelTest {
    @Autowired
    private GraphModel graphModel;

    @Test
    void GraphModel_GetGraphDataFromSinX_SizeShouldBe10000() {
        GraphData graphData = graphModel.getGraphData(-30, 30, "sin(x)");
        Assertions.assertEquals(10000, graphData.getXValues().size());
    }

    @Test
    void GraphModel_GetGraphDataFromSqrtX_SizeShouldBe5000() {
        GraphData graphData = graphModel.getGraphData(-30, 30, "sqrt(x)");
        Assertions.assertEquals(5000, graphData.getXValues().size());
    }

    @Test
    void GraphModel_GetGraphDataFromIncorrectData_SizeShouldBe0() {
        GraphData graphData = graphModel.getGraphData(-30, 30, "ERROR");
        Assertions.assertEquals(0, graphData.getXValues().size());
    }
}