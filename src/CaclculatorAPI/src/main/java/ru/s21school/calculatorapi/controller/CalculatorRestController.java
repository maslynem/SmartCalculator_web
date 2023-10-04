package ru.s21school.calculatorapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.s21school.calculatorapi.service.CalculatorService;
import ru.s21school.calculatorapi.controller.request.ExpressionRequest;
import ru.s21school.calculatorapi.controller.request.GraphRequest;
import ru.s21school.calculatorapi.controller.responce.*;
import ru.s21school.calculatorapi.service.model.graphModel.GraphData;
import ru.s21school.calculatorapi.service.GraphService;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CalculatorRestController {
    private final CalculatorService calculatorServiceImpl;
    private final GraphService graphServiceImpl;

    @PostMapping("/calculate")
    public CalculatorResponse calculate(@RequestBody ExpressionRequest request) {
        log.info("Expression: {}", request.getExpression());
        double result = calculatorServiceImpl.calculate(request.getExpression());
        log.info("Result: {}", result);
        return CalculatorResponse.builder()
                .expression(request.getExpression())
                .result(result)
                .status(Status.OK)
                .build();
    }

    @PostMapping("/graph")
    public GraphResponse calculateGraphCoordinates(@RequestBody GraphRequest request) {
        log.info("Graph: minX: {}, maxX: {}, expression: {}", request.getMinX(), request.getMaxX(), request.getExpression());
        GraphData graphData = graphServiceImpl.calculateCoordinates(request.getMinX(), request.getMaxX(), request.getExpression());
        log.info("Graph calculate coordinates successfully");
        return GraphResponse.builder()
                .status(Status.OK)
                .xValues(graphData.getXValues())
                .yValues(graphData.getYValues())
                .build();
    }

    @ExceptionHandler(Exception.class)
    private CalculatorErrorResponse handleException(Exception exception) {
        log.warn(exception.getMessage());
        return CalculatorErrorResponse.builder()
                .message(exception.getMessage())
                .status(Status.ERROR)
                .build();
    }
}
