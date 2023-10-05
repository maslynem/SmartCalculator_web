package ru.s21school.creditcalculatorapi.model.annuityCreditCalculator;

import ru.s21school.creditcalculatorapi.model.CreditInputData;

public interface AnnuityCreditCalculator {
    AnnuityCredit countAnnuityCredit(CreditInputData creditInputData);
}
