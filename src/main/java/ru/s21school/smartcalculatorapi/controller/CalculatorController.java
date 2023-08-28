package ru.s21school.smartcalculatorapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.s21school.smartcalculatorapi.controller.request.CalculatorRequest;
import ru.s21school.smartcalculatorapi.controller.responce.CalculatorErrorResponse;
import ru.s21school.smartcalculatorapi.controller.responce.CalculatorResponse;
import ru.s21school.smartcalculatorapi.controller.responce.Status;
import ru.s21school.smartcalculatorapi.service.CalculatorService;

@Slf4j
@RestController
@RequestMapping("/calculator/api/v1")
@RequiredArgsConstructor
public class CalculatorController {
    private final CalculatorService calculatorService;

    @PostMapping
    public CalculatorResponse calculate(@RequestBody CalculatorRequest request) {

        log.info("Expression: {}", request.getExpression());
        double result = calculatorService.calculate(request.getExpression(), request.getHistoryUUID());
        log.info("Result: {}", result);

        return CalculatorResponse.builder()
                .expression(request.getExpression())
                .result(result)
                .status(Status.OK)
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
