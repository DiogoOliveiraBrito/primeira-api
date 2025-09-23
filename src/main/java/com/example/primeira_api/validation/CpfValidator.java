package com.example.primeira_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CpfValidator implements ConstraintValidator<CpfValido, String> {

    @Override
    public void initialize(CpfValido constraintAnnotation) { }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return true;
        }
        cpf = cpf.replaceAll("[^0-9]", "");

        CPFValidator validator = new CPFValidator();
        validator.initialize(null);

        return validator.isValid(cpf, context);
    }
}