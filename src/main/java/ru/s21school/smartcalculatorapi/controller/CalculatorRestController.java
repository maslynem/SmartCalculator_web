package ru.s21school.smartcalculatorapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.s21school.smartcalculatorapi.controller.request.CalculatorRequest;
import ru.s21school.smartcalculatorapi.controller.request.GraphRequest;
import ru.s21school.smartcalculatorapi.controller.request.HistoryRequest;
import ru.s21school.smartcalculatorapi.controller.responce.*;
import ru.s21school.smartcalculatorapi.dto.HistoryDto;
import ru.s21school.smartcalculatorapi.model.graphModel.GraphData;
import ru.s21school.smartcalculatorapi.service.CalculatorService;
import ru.s21school.smartcalculatorapi.service.GraphService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/calculator/api/v1")
@RequiredArgsConstructor
public class CalculatorRestController {
    private final CalculatorService calculatorService;
    private final GraphService graphService;

    @PostMapping
    public CalculatorResponse calculate(@RequestBody CalculatorRequest request) {
        log.info("Expression: {}", request.getExpression());
        log.info("User UUID: {}", request.getUserUUID());
        double result = calculatorService.calculate(request.getExpression(), request.getUserUUID());
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
        GraphData graphData = graphService.calculateCoordinates(request.getMinX(), request.getMaxX(), request.getExpression());
        return GraphResponse.builder()
                .status(Status.OK)
                .xvalues(graphData.getXvalues())
                .yvalues(graphData.getYvalues())
                .build();
    }

    @PostMapping("/history")
    public HistoryResponse findUserHistory(@RequestBody HistoryRequest request) {
        log.info("/history. User UUID: {}", request.getUserUUID());
        List<HistoryDto> userHistory = calculatorService.findUserHistory(request.getUserUUID());
        log.info("/history. Size of history: {}", userHistory.size());
        HistoryResponse historyResponse = new HistoryResponse();
        userHistory.stream().map(HistoryDto::getExpression).forEach(historyResponse::addToHistory);
        historyResponse.setStatus(Status.OK);
        return historyResponse;
    }

    @PostMapping("/history/clear")
    public HistoryResponse deleteUserHistory(@RequestBody HistoryRequest request) {
        log.info("/history/clear. User UUID: {}", request.getUserUUID());
        calculatorService.deleteUserHistory(request.getUserUUID());
        return HistoryResponse.builder().status(Status.OK).build();
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
