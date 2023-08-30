package ru.s21school.smartcalculatorapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.s21school.smartcalculatorapi.dto.HistoryDto;
import ru.s21school.smartcalculatorapi.entity.History;
import ru.s21school.smartcalculatorapi.entity.User;
import ru.s21school.smartcalculatorapi.model.calculator.Calculator;
import ru.s21school.smartcalculatorapi.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculatorService {
    private final Calculator calculator;
    private final UserRepository userRepository;

    public double calculate(String expression, String userUUID) {
        double result = calculator.calculate(expression);
        User user = userRepository.findByUuid(userUUID)
                .orElse(User.builder().uuid(userUUID).build());
        History history = new History();
        history.setUser(user);
        history.setExpression(expression);
        history.setResult(result);
        user.getHistories().add(history);
        userRepository.save(user);
        userRepository.flush();
        return result;
    }

    public List<HistoryDto> findUserHistory(String userUUID) {
        ModelMapper mapper = new ModelMapper();
        return userRepository.findByUuid(userUUID)
                .map(user -> user.getHistories().stream()
                        .map(history -> mapper.map(history, HistoryDto.class))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
