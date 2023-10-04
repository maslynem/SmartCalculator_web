package ru.s21school.usershistory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private String uuid;
    private List<HistoryDto> histories;
}