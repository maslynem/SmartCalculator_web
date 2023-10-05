package ru.s21school.smartcalculatorserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.s21school.smartcalculatorserver.controller.request.*;

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
    public ResponseEntity<Object> calculate(@RequestBody CalculatorRequest request) {
        log.info("Expression: {}", request.getExpression());
        log.info("User UUID: {}", request.getUserUUID());
        ResponseEntity<Object> calculatorResponse = restTemplate.postForEntity(CALCULATOR_URL, new ExpressionRequest(request.getExpression()), Object.class);
        try {
            restTemplate.postForLocation(HISTORY_URL + request.getUserUUID(), new HistoryRequest(request.getExpression()));
        } catch (Exception e) {
            log.error("Can't save history for user UUID: {}, error response:: {}", request.getUserUUID(), ExceptionUtils.getRootCauseMessage(e));
        }
        log.info("Result: {}", calculatorResponse.getBody());
        return calculatorResponse;
    }

    @GetMapping("/history/{UUID}")
    public ResponseEntity<Object> findUserHistory(@PathVariable String UUID) {
        log.info("/history. User UUID: {}", UUID);
        try {
            return restTemplate.getForEntity(HISTORY_URL + UUID, Object.class);
        } catch (Exception e) {
            log.error("Can't get history for user UUID: {}, error response:: {}", UUID, ExceptionUtils.getRootCauseMessage(e));
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/history/{UUID}")
    public void deleteUserHistory(@PathVariable String UUID) {
        log.info("/history/clear. User UUID: {}", UUID);
        try {
            restTemplate.delete(HISTORY_URL + UUID);
        } catch (Exception e) {
            log.error("Can't delete history for user UUID: {}, error response:: {}", UUID, ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping("/graph")
    public ResponseEntity<Object> calculateGraphCoordinates(@RequestBody GraphRequest request) {
        log.info("Graph: minX: {}, maxX: {}, expression: {}", request.getMinX(), request.getMaxX(), request.getExpression());
        return restTemplate.postForEntity(GRAPH_URL, request, Object.class);
    }

    @PostMapping("/credit/ann")
    public ResponseEntity<Object> calculateAnnuityCredit(@RequestBody CreditRequest creditRequest) {
        log.info("POST /credit/ann");
        log.info("Credit Input Data: {}", creditRequest);
        return restTemplate.postForEntity(CREDIT_URL + "ann", creditRequest, Object.class);
    }

    @PostMapping("/credit/diff")
    public ResponseEntity<Object> calculateDifferentiatedCredit(@RequestBody CreditRequest creditRequest) {
        log.info("POST /credit/diff");
        log.info("Credit Input Data: {}", creditRequest);
        return restTemplate.postForEntity(CREDIT_URL + "diff", creditRequest, Object.class);
    }
}
