package ru.s21school.creditcalculatorapi.model.annuityCreditCalculator;

import org.springframework.stereotype.Component;
import ru.s21school.creditcalculatorapi.model.CreditInputData;

@Component
public class AnnuityCreditCalculatorImpl implements AnnuityCreditCalculator {
    @Override
    public AnnuityCredit countAnnuityCredit(CreditInputData creditInputData) {
        double sum = creditInputData.getCreditSum();
        double rate = creditInputData.getInterestRate();
        double date = creditInputData.getCreditTerm() * creditInputData.getTermCoefficient();

        if (sum <= 0 || rate <= 0 || date <= 0) {
            throw new IllegalArgumentException("Input data can not be less than 0");
        }
        double i = rate / 1200;
        double monthPay = sum * ((i * Math.pow((1 + i), date)) / (Math.pow((1 + i), date) - 1));
        monthPay = Math.round(monthPay * 100) / 100.;
        double debt = monthPay * date - sum;
        double allSum = monthPay * date;
        return new AnnuityCredit(allSum, debt, monthPay);
    }
}
