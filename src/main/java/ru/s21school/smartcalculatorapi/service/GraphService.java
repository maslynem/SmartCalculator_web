package ru.s21school.smartcalculatorapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.s21school.smartcalculatorapi.model.graphModel.GraphData;
import ru.s21school.smartcalculatorapi.model.graphModel.GraphModel;

@Service
@RequiredArgsConstructor
public class GraphService {
    private final GraphModel graphModel;

    public GraphData calculateCoordinates(int minX, int maxX, String expression) {
        return graphModel.getGraphData(minX, maxX, expression);
    }
}
