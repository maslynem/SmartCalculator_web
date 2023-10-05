package ru.s21school.creditcalculatorapi.model.annuityCreditCalculator;

import org.junit.jupiter.api.Test;
import ru.s21school.creditcalculatorapi.model.CreditInputData;

import static org.junit.jupiter.api.Assertions.*;

class AnnuityCreditCalculatorUnitTests {
    private AnnuityCreditCalculator calculator = new AnnuityCreditCalculatorImpl();

    @Test
    void testAnnuityCreditByTenYears() {
        CreditInputData creditInputData = CreditInputData.builder()
                .creditSum(1_000_000)
                .creditTerm(10)
                .termCoefficient(12)
                .interestRate(4.5)
                .build();
        AnnuityCredit annuityCredit = calculator.countAnnuityCredit(creditInputData);
        assertNotNull(annuityCredit);
        assertEquals(10363.84, annuityCredit.getMonthPay(), 1e-2);
        assertEquals(243660.8, annuityCredit.getDebt(), 1e-2);
        assertEquals(1243660.8, annuityCredit.getAllSum(), 1e-2);
    }

    @Test
    void testAnnuityCreditByTenMonth() {
        CreditInputData creditInputData = CreditInputData.builder()
                .creditSum(1_000_000)
                .creditTerm(10)
                .termCoefficient(1)
                .interestRate(4.5)
                .build();
        AnnuityCredit annuityCredit = calculator.countAnnuityCredit(creditInputData);
        assertNotNull(annuityCredit);
        assertEquals(102074.08, annuityCredit.getMonthPay(), 1e-2);
        assertEquals(20740.8, annuityCredit.getDebt(), 1e-2);
        assertEquals(1020740.8, annuityCredit.getAllSum(), 1e-2);
    }

    @Test
    void testAnnuityCreditShouldThrowException() {
        CreditInputData creditInputData = CreditInputData.builder()
                .creditSum(-1_000_000)
                .creditTerm(10)
                .termCoefficient(1)
                .interestRate(4.5)
                .build();
        assertThrows(IllegalArgumentException.class, () -> calculator.countAnnuityCredit(creditInputData));
    }
}
