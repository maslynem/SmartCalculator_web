package ru.s21school.usershistory.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/{userUUID}")
    public void addUserExpression(@PathVariable String userUUID,
                                  @RequestBody HistoryRequest request) {
        log.info("POST /history/{}", userUUID);
        historyService.addUserHistory(userUUID, request.getExpression());
        log.info("POST /history/{userUUID} successful");
    }

    @GetMapping("/{userUUID}")
    public HistoryResponse findUserHistory(@PathVariable String userUUID) {
        log.info("GET /history/{}", userUUID);
        List<HistoryDto> userHistory = historyService.findUserHistory(userUUID);
        log.info("GET /history. Size of history: {}", userHistory.size());
        HistoryResponse historyResponse = new HistoryResponse();
        userHistory.stream().map(HistoryDto::getExpression).forEach(historyResponse::addToHistory);
        historyResponse.setStatus(Status.OK);
        return historyResponse;
    }

    @DeleteMapping("/{userUUID}")
    public void deleteUserHistory(@PathVariable String userUUID) {
        log.info("/history/clear. User UUID: {}", userUUID);
        historyService.deleteUserHistory(userUUID);
    }

}
