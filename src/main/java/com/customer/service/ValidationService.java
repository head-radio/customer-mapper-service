package com.customer.service;

import com.customer.IValidationService;
import com.customer.exception.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class ValidationService implements IValidationService {

    @Autowired
    private Validator validator;

    /**
     * validate object using javax validator
     * @param object
     */
    @Override
    public void validateObject(Object object) {

        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            Map<String, Object> body = new LinkedHashMap<>();
            for (ConstraintViolation<Object> customerRequestConstraintViolation : violations) {
                body.put(customerRequestConstraintViolation.getMessageTemplate(), customerRequestConstraintViolation.getMessage());
            }
            throw new BadRequestException("invalid input data", body);
        }

    }

}
