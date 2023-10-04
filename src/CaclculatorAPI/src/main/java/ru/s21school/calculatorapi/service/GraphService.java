package ru.s21school.calculatorapi.service;

import ru.s21school.calculatorapi.service.model.graphModel.GraphData;

public interface GraphService {
    GraphData calculateCoordinates(int minX, int maxX, String expression);
}
