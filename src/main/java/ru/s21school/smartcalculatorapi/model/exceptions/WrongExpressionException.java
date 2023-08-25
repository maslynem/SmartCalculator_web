package ru.s21school.smartcalculatorapi.model.exceptions;

public class WrongExpressionException extends RuntimeException {
    public WrongExpressionException(String message) {
        super(message);
    }
}
