package ru.s21school.usershistory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.s21school.usershistory.dto.HistoryDto;
import ru.s21school.usershistory.repository.HistoryRepository;
import ru.s21school.usershistory.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService {
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public List<HistoryDto> findUserHistory(String userUUID) {
        ModelMapper mapper = new ModelMapper();
        return userRepository.findByUuid(userUUID)
                .map(user -> user.getHistories().stream()
                        .map(history -> mapper.map(history, HistoryDto.class))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Transactional
    public void deleteUserHistory(String userUUID) {
        historyRepository.deleteByUser(userUUID);
    }
}
