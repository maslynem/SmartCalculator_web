package ru.s21school.calculatorapi.service.model.graphModel;


public interface Graph {
    GraphData getGraphData(int minX, int maxX, String expression);
}
