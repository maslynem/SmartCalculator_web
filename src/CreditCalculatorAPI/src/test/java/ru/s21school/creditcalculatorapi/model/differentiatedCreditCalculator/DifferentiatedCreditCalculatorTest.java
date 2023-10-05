package ru.s21school.creditcalculatorapi.model.differentiatedCreditCalculator;

import org.junit.jupiter.api.Test;
import ru.s21school.creditcalculatorapi.model.CreditInputData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DifferentiatedCreditCalculatorUnitTest {
    private DifferentiatedCreditCalculator calculator = new DifferentiatedCreditCalculatorImpl();

    @Test
    void testDifferentiatedCreditByTenYears() {
        CreditInputData creditInputData = CreditInputData.builder()
                .creditSum(1_000_000)
                .creditTerm(10)
                .termCoefficient(12)
                .interestRate(4.5)
                .build();
        DifferentiatedCredit differentiatedCredit = calculator.countDifferentiatedCredit(creditInputData);
        assertNotNull(differentiatedCredit);
        assertEquals(1226875, differentiatedCredit.getAllSum(), 1e-2);
        assertEquals(226875, differentiatedCredit.getDebt(), 1e-2);

        List<Double> monthPay = differentiatedCredit.getMonthPay();
        assertFalse(monthPay.isEmpty());
        assertEquals(120, monthPay.size());
        assertEquals(12083.33, monthPay.get(0), 1e-2);
        assertEquals(8364.58, monthPay.get(monthPay.size()-1), 1e-2);
    }

    @Test
    void testDifferentiatedCreditByTenMonth() {
        CreditInputData creditInputData = CreditInputData.builder()
                .creditSum(1_000_000)
                .creditTerm(10)
                .termCoefficient(1)
                .interestRate(4.5)
                .build();
        DifferentiatedCredit differentiatedCredit = calculator.countDifferentiatedCredit(creditInputData);
        assertNotNull(differentiatedCredit);
        assertEquals(1020625, differentiatedCredit.getAllSum(), 1e-2);
        assertEquals(20625, differentiatedCredit.getDebt(), 1e-2);

        List<Double> monthPay = differentiatedCredit.getMonthPay();
        assertFalse(monthPay.isEmpty());
        assertEquals(10, monthPay.size());
        assertEquals(103750, monthPay.get(0), 1e-2);
        assertEquals(100375, monthPay.get(monthPay.size()-1), 1e-2);
    }

    @Test
    void testAnnuityCreditShouldThrowException() {
        CreditInputData creditInputData = CreditInputData.builder()
                .creditSum(-1_000_000)
                .creditTerm(10)
                .termCoefficient(1)
                .interestRate(4.5)
                .build();
        assertThrows(IllegalArgumentException.class, () -> calculator.countDifferentiatedCredit(creditInputData));
    }
}