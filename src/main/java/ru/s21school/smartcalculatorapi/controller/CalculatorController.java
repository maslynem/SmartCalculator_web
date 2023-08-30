package ru.s21school.smartcalculatorapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.s21school.smartcalculatorapi.controller.request.CalculatorRequest;
import ru.s21school.smartcalculatorapi.controller.request.HistoryRequest;
import ru.s21school.smartcalculatorapi.controller.responce.CalculatorErrorResponse;
import ru.s21school.smartcalculatorapi.controller.responce.CalculatorResponse;
import ru.s21school.smartcalculatorapi.controller.responce.HistoryResponse;
import ru.s21school.smartcalculatorapi.controller.responce.Status;
import ru.s21school.smartcalculatorapi.dto.HistoryDto;
import ru.s21school.smartcalculatorapi.service.CalculatorService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/calculator/api/v1")
@RequiredArgsConstructor
public class CalculatorController {
    private final CalculatorService calculatorService;

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

    @PostMapping("/history")
    public HistoryResponse findHistory(@RequestBody HistoryRequest request) {
        log.info("/history. User UUID: {}", request.getUserUUID());
        List<HistoryDto> userHistory = calculatorService.findUserHistory(request.getUserUUID());
        log.info("/history. Size of history: {}", userHistory.size());
        HistoryResponse historyResponse = new HistoryResponse();
        userHistory.stream().map(HistoryDto::getExpression).forEach(historyResponse::addToHistory);
        historyResponse.setStatus(Status.OK);
        return historyResponse;
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
