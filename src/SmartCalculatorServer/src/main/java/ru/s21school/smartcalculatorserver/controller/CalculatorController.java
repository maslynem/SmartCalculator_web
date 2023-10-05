package ru.s21school.smartcalculatorserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.s21school.smartcalculatorserver.controller.request.*;
import ru.s21school.smartcalculatorserver.controller.responce.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/v1/calculator")
public class CalculatorController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CALCULATOR_URL = "http://calculator/api/v1/calculate/";
    private static final String GRAPH_URL = "http://calculator/api/v1/graph/";
    private static final String HISTORY_URL = "http://history/api/v1/history/";
    private static final String CREDIT_URL = "http://credit-calculator/api/v1/credit-calculator/";

    @PostMapping
    public CalculatorResponse calculate(@RequestBody CalculatorRequest request) {
        log.info("Expression: {}", request.getExpression());
        log.info("User UUID: {}", request.getUserUUID());

        CalculatorResponse calculatorResponse = restTemplate.postForObject(CALCULATOR_URL, new ExpressionRequest(request.getExpression()), CalculatorResponse.class);
        restTemplate.postForLocation(HISTORY_URL + request.getUserUUID(), new HistoryRequest(request.getExpression(), calculatorResponse.getResult()));

        log.info("Result: {}", calculatorResponse.getResult());
        return calculatorResponse;
    }

    @GetMapping("/history/{UUID}")
    public HistoryResponse findUserHistory(@PathVariable String UUID) {
        log.info("/history. User UUID: {}", UUID);
        HistoryResponse historyResponse = restTemplate.getForObject(HISTORY_URL + UUID, HistoryResponse.class);
        log.info("/history. Size of history: {}", historyResponse.getHistory().size());
        return historyResponse;
    }

    @DeleteMapping("/history/{UUID}")
    public void deleteUserHistory(@PathVariable String UUID) {
        log.info("/history/clear. User UUID: {}", UUID);
        restTemplate.delete(HISTORY_URL + UUID);
    }

    @PostMapping("/graph")
    public GraphResponse calculateGraphCoordinates(@RequestBody GraphRequest request) {
        log.info("Graph: minX: {}, maxX: {}, expression: {}", request.getMinX(), request.getMaxX(), request.getExpression());
        return restTemplate.postForObject(GRAPH_URL, request, GraphResponse.class);
    }

    @PostMapping("/credit/ann")
    public AnnuityCreditResponse calculateAnnuityCredit(@RequestBody CreditRequest creditRequest) {
        log.info("POST /credit/ann");
        log.info("Credit Input Data: {}", creditRequest);
        return restTemplate.postForObject(CREDIT_URL + "ann", creditRequest, AnnuityCreditResponse.class);
    }

    @PostMapping("/credit/diff")
    public DifferentiatedCreditResponse calculateDifferentiatedCredit(@RequestBody CreditRequest creditRequest) {
        log.info("POST /credit/diff");
        log.info("Credit Input Data: {}", creditRequest);
        return restTemplate.postForObject(CREDIT_URL + "diff", creditRequest, DifferentiatedCreditResponse.class);
    }

}
