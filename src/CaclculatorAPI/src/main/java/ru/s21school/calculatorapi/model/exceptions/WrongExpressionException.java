package ru.s21school.calculatorapi.model.exceptions;

public class WrongExpressionException extends RuntimeException {
    public WrongExpressionException(String message) {
        super(message);
    }
}
