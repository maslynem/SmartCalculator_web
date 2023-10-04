package ru.s21school.smartcalculatorserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.s21school.smartcalculatorserver.controller.request.CalculatorRequest;
import ru.s21school.smartcalculatorserver.controller.request.ExpressionRequest;
import ru.s21school.smartcalculatorserver.controller.request.HistoryRequest;
import ru.s21school.smartcalculatorserver.controller.responce.CalculatorResponse;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/v1/calculator")
public class CalculatorController {

    @Autowired
    private RestTemplate restTemplate;

    private final String CALCULATOR_URL = "http://calculator/api/v1/calculate/";
    private final String GRAPH_URL = "http://calculator/api/v1/graph/";
    private final String HISTORY_URL = "http://history/api/v1/history/";

    @PostMapping
    public CalculatorResponse calculate(@RequestBody CalculatorRequest request) {
        log.info("Expression: {}", request.getExpression());
        log.info("User UUID: {}", request.getUserUUID());

        CalculatorResponse calculatorResponse = restTemplate.postForObject(CALCULATOR_URL, new ExpressionRequest(request.getExpression()), CalculatorResponse.class);
        restTemplate.postForLocation(HISTORY_URL+request.getUserUUID(), new HistoryRequest(request.getExpression(), calculatorResponse.getResult()));
        log.info("Result: {}", calculatorResponse.getResult());
        return calculatorResponse;
    }

//
//    @PostMapping("/graph")
//    public GraphResponse calculateGraphCoordinates(@RequestBody GraphRequest request) {
//        log.info("Graph: minX: {}, maxX: {}, expression: {}", request.getMinX(), request.getMaxX(), request.getExpression());
//        GraphData graphData = graphService.calculateCoordinates(request.getMinX(), request.getMaxX(), request.getExpression());
//        return GraphResponse.builder()
//                .status(Status.OK)
//                .xvalues(graphData.getXvalues())
//                .yvalues(graphData.getYvalues())
//                .build();
//    }
//
//    @PostMapping("/history")
//    public HistoryResponse findUserHistory(@RequestBody HistoryRequest request) {
//        log.info("/history. User UUID: {}", request.getUserUUID());
//        List<HistoryDto> userHistory = calculatorService.findUserHistory(request.getUserUUID());
//        log.info("/history. Size of history: {}", userHistory.size());
//        HistoryResponse historyResponse = new HistoryResponse();
//        userHistory.stream().map(HistoryDto::getExpression).forEach(historyResponse::addToHistory);
//        historyResponse.setStatus(Status.OK);
//        return historyResponse;
//    }
//
//    @PostMapping("/history/clear")
//    public HistoryResponse deleteUserHistory(@RequestBody HistoryRequest request) {
//        log.info("/history/clear. User UUID: {}", request.getUserUUID());
//        calculatorService.deleteUserHistory(request.getUserUUID());
//        return HistoryResponse.builder().status(Status.OK).build();
//    }
}
