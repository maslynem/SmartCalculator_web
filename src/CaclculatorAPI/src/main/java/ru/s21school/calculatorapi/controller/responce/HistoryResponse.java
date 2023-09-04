package ru.s21school.calculatorapi.controller.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class HistoryResponse {
    private Status status;
    private List<String> history;

    public HistoryResponse() {
        history = new ArrayList<>();
    }

    public void addToHistory(String expression) {
        history.add(expression);
    }

}
