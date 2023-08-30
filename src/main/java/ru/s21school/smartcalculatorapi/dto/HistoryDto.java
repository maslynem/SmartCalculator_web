package ru.s21school.smartcalculatorapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto implements Serializable {
    private  String userUuid;
    private  String expression;
    private  Double result;
}