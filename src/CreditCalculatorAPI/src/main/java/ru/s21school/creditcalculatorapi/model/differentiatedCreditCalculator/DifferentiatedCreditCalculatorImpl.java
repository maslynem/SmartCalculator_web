package ru.s21school.creditcalculatorapi.model.differentiatedCreditCalculator;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;
import ru.s21school.creditcalculatorapi.model.CreditInputData;

import java.util.ArrayList;
import java.util.List;

@Component
public class DifferentiatedCreditCalculatorImpl implements DifferentiatedCreditCalculator {
    @Override
    public DifferentiatedCredit countDifferentiatedCredit(CreditInputData creditInputData) {
        double sum = creditInputData.getCreditSum();
        double rate = creditInputData.getInterestRate();
        double date = creditInputData.getCreditTerm() * creditInputData.getTermCoefficient();

        if (sum <= 0 || rate <= 0 || date <= 0) {
            throw new IllegalArgumentException("Input data can not be less than 0");
        }
        double allSum = 0;
        double debt = 0;
        List<Double> monthPay = new ArrayList<>();
        for (int i = 1; i <= date; i++) {
            double temp = (sum - (sum / date) * (i - 1));
            double tempMonthPay = sum / date + temp * (rate / 1200);
            allSum += tempMonthPay;
            monthPay.add(Precision.round(tempMonthPay, 2));
            if (i == date) {
                debt = allSum - sum;
            }
        }
        debt = Precision.round(debt, 2);
        allSum = Precision.round(allSum, 2);
        return new DifferentiatedCredit(allSum, debt, monthPay);
    }
}
