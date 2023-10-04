package ru.s21school.creditcalculatorapi.model.creditCalculator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditCalculatorImpl implements CreditCalculator {
    public AnnuityCredit countAnnuityCredit(CreditInputData creditInputData) {
        double sum = creditInputData.getCreditSum();
        double rate = creditInputData.getInterestRate();
        double date = creditInputData.getCreditTerm() * creditInputData.getTermCoefficient();

        if (sum <= 0 || rate <= 0 || date <= 0) {
            throw new IllegalArgumentException();
        }
        double i = rate / 1200;
        double monthPay = sum * ((i * Math.pow((1 + i), date)) / (Math.pow((1 + i), date) - 1));
        monthPay = Math.round(monthPay * 100) / 100.;
        double debt = monthPay * date - sum;
        double allSum = monthPay * date;
        return new AnnuityCredit(allSum, debt, monthPay);
    }

    public DifferentiatedCredit countDifferentiatedCredit(CreditInputData creditInputData) {
        double sum = creditInputData.getCreditSum();
        double rate = creditInputData.getInterestRate();
        double date = creditInputData.getCreditTerm() * creditInputData.getTermCoefficient();

        if (sum <= 0 || rate <= 0 || date <= 0) {
            throw new IllegalArgumentException();
        }
        double allSum = 0;
        double debt = 0;
        List<Double> monthPay = new ArrayList<>();
        for (int i = 1; i <= date; i++) {
            double temp = (sum - (sum / date) * (i - 1));
            double tempMonthPay = sum / date + temp * (rate / 1200);
            tempMonthPay = Math.round(tempMonthPay * 100) / 100.;
            monthPay.add(tempMonthPay);
            allSum += tempMonthPay;
            if (i == date) {
                debt = allSum - sum;
            }
        }
        debt = Math.round(debt * 100) / 100.;
        allSum = Math.round(allSum * 100) / 100.;
        return new DifferentiatedCredit(allSum, debt, monthPay);
    }
}
