package ru.s21school.calculatorapi.service.model.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FromInfixToPostfixTransformerTest {
    @ParameterizedTest
    @CsvSource(value = {
            "sqrt(x)-1/2*sin(x^2-2), x sqrt 1 2 / x 2 ^ 2 - sin * -",
            "x+(4*6+x)*6, x 4 6 * x + 6 * +",
            "x + 213 * 23 + 12, x 213 23 * + 12 +",
            "(x + 213) * (23 + 12), x 213 + 23 12 + *",
            "x * 213 + 23 * 12, x 213 * 23 12 * +",
            "x + 213 + 23 + 12, x 213 + 23 + 12 +",
            "x + 213 + 23 + 12, x 213 + 23 + 12 +",
            "(1+2)*4*cos(x*7-2)+sin(2*x), 1 2 + 4 * x 7 * 2 - cos * 2 x * sin +",
            "sqrt(x*log(5-x))+log(55/(2+x)), x 5 x - log * sqrt 55 2 x + / log +",
            "2^3^2, 2 3 2 ^ ^",
            "100.235+x-(x+10), 100.235 x + x 10 + -",
    })
    void transform(String infix, String postfix) {
        List<String> transform = FromInfixToPostfixTransformer.transform(infix);
        String result = String.join(" ", transform);
        assertEquals(postfix, result);
    }
}