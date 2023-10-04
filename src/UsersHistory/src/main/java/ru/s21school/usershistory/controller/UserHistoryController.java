package ru.s21school.usershistory.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.s21school.usershistory.controller.request.HistoryRequest;
import ru.s21school.usershistory.controller.responce.HistoryResponse;
import ru.s21school.usershistory.controller.responce.Status;
import ru.s21school.usershistory.dto.HistoryDto;
import ru.s21school.usershistory.service.HistoryService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class UserHistoryController {

    private final HistoryService historyService;

    @PostMapping
    public HistoryResponse findUserHistory(@RequestBody HistoryRequest request) {
        log.info("/history. User UUID: {}", request.getUserUUID());
        List<HistoryDto> userHistory = historyService.findUserHistory(request.getUserUUID());
        log.info("/history. Size of history: {}", userHistory.size());
        HistoryResponse historyResponse = new HistoryResponse();
        userHistory.stream().map(HistoryDto::getExpression).forEach(historyResponse::addToHistory);
        historyResponse.setStatus(Status.OK);
        return historyResponse;
    }

    @PostMapping("/history/clear")
    public HistoryResponse deleteUserHistory(@RequestBody HistoryRequest request) {
        log.info("/history/clear. User UUID: {}", request.getUserUUID());
        historyService.deleteUserHistory(request.getUserUUID());
        return HistoryResponse.builder().status(Status.OK).build();
    }

}
