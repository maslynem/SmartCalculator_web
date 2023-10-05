package ru.s21school.creditcalculatorapi.model.differentiatedCreditCalculator;

import ru.s21school.creditcalculatorapi.model.CreditInputData;

public interface DifferentiatedCreditCalculator {
    DifferentiatedCredit countDifferentiatedCredit(CreditInputData creditInputData);
}
