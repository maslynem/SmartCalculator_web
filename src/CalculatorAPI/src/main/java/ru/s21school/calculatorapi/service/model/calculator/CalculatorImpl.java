package ru.s21school.calculatorapi.service.model.calculator;

import org.springframework.stereotype.Component;
import ru.s21school.calculatorapi.service.model.exceptions.WrongExpressionException;
import ru.s21school.calculatorapi.service.model.utils.StringUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class CalculatorImpl implements Calculator {

    private static final Map<String, BiFunction<Double, Double, Double>> operatorsMap;
    private static final Map<String, Function<Double, Double>> functionsMap;

    static {
        operatorsMap = new HashMap<>();
        operatorsMap.put("+", Double::sum);
        operatorsMap.put("-", (a, b) -> a - b);
        operatorsMap.put("*", (a, b) -> a * b);
        operatorsMap.put("/", (a, b) -> a / b);
        operatorsMap.put("^", Math::pow);
        operatorsMap.put("%", (a, b) -> a % b);

        functionsMap = new HashMap<>();
        functionsMap.put("acos", Math::acos);
        functionsMap.put("asin", Math::asin);
        functionsMap.put("atan", Math::atan);
        functionsMap.put("cos", Math::cos);
        functionsMap.put("ln", Math::log);
        functionsMap.put("log", Math::log10);
        functionsMap.put("sin", Math::sin);
        functionsMap.put("sqrt", Math::sqrt);
        functionsMap.put("tan", Math::tan);
    }

    public double calculate(String expression) {
        List<String> postfix = FromInfixToPostfixTransformer.transform(expression);
        return calculateByPostfix(postfix);
    }

    private double calculateByPostfix(List<String> postfix) {
        try {
            Deque<Double> stack = new ArrayDeque<>();
            for (String token : postfix) {
                if (StringUtils.isOperand(token)) {
                    stack.push(Double.parseDouble(token));
                } else if (StringUtils.isOperator(token)) {
                    stack.push(executeOperator(stack, token));
                } else if (StringUtils.isFunction(token)) {
                    stack.push(executeFunction(stack, token));
                } else {
                    throw new WrongExpressionException("Wrong expression: undefined token [" + token + "]");
                }
            }
            double result = stack.pop();
            if (!stack.isEmpty()) {
                throw new WrongExpressionException("Wrong expression: missing operator");
            }
            return result;
        } catch (NoSuchElementException exception) {
            throw new WrongExpressionException("Wrong expression: unexpected operator");
        }
    }

    private double executeOperator(Deque<Double> stack, String operator) {
        if (operator.equals("~")) {
            return 0 - stack.pop();
        } else {
            double secondOperand = stack.pop();
            double firstOperand = stack.pop();
            return operatorsMap.getOrDefault(operator, (a, b) -> Double.NaN).apply(firstOperand, secondOperand);

        }
    }

    private double executeFunction(Deque<Double> stack, String function) {
        double operand = stack.pop();
        return functionsMap.getOrDefault(function, ignored -> Double.NaN).apply(operand);
    }
}
