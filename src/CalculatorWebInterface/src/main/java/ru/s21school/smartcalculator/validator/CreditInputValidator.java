package ru.s21school.smartcalculator.validator;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.s21school.smartcalculator.model.creditCalculator.CreditInputData;

@Component
public class CreditInputValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return CreditInputData.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        CreditInputData obj = (CreditInputData) target;
        if (obj.getCreditSum() <= 0) {
            errors.rejectValue("creditSum", "", "Сумма кредита должна быть больше 0");
        }
        if (obj.getCreditTerm() <= 0) {
            errors.rejectValue("creditTerm", "", "Срок кредита должен быть больше 0");
        }
        if (obj.getInterestRate() <= 0) {
            errors.rejectValue("interestRate", "", "Процентная ставка должна быть больше 0");
        }
    }
}
