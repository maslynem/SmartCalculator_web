package ru.s21school.calculatorapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.s21school.calculatorapi.service.model.graphModel.Graph;
import ru.s21school.calculatorapi.service.model.graphModel.GraphData;

@Service
@RequiredArgsConstructor
class GraphServiceImpl implements GraphService {
    private final Graph graphModel;

    @Override
    public GraphData calculateCoordinates(int minX, int maxX, String expression) {
        return graphModel.getGraphData(minX, maxX, expression);
    }
}
