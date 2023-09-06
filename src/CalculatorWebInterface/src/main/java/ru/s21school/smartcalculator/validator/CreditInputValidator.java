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
        if (obj.getCreditSum() == null) {
            errors.rejectValue("creditSum", "", "Введите сумму кредита");
        } else if (obj.getCreditSum() <= 0) {
            errors.rejectValue("creditSum", "", "Сумма кредита должна быть больше 0");
        }

        if (obj.getCreditTerm() == null) {
            errors.rejectValue("creditTerm", "", "Введите срок кредита");
        } else if (obj.getCreditTerm() <= 0) {
            errors.rejectValue("creditTerm", "", "Срок кредита должен быть больше 0");
        }

        if (obj.getInterestRate() == null) {
            errors.rejectValue("interestRate", "", "Введите процентную ставку");
        } else if (obj.getInterestRate() <= 0) {
            errors.rejectValue("interestRate", "", "Процентная ставка должна быть больше 0");
        }
    }
}
