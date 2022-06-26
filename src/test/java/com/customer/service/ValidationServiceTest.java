package com.customer.service;

import com.customer.exception.BadRequestException;
import com.customer.model.CustomerRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;

    @Mock
    private Validator validator;

    @Test(expected = BadRequestException.class)
    public void validateObjectTest() {

        CustomerRequest customerRequest = new CustomerRequest();
        ConstraintViolation constraintViolation = new ConstraintViolation() {
            @Override
            public String getMessage() {
                return null;
            }

            @Override
            public String getMessageTemplate() {
                return null;
            }

            @Override
            public Object getRootBean() {
                return null;
            }

            @Override
            public Class getRootBeanClass() {
                return null;
            }

            @Override
            public Object getLeafBean() {
                return null;
            }

            @Override
            public Object[] getExecutableParameters() {
                return new Object[0];
            }

            @Override
            public Object getExecutableReturnValue() {
                return null;
            }

            @Override
            public Path getPropertyPath() {
                return null;
            }

            @Override
            public Object getInvalidValue() {
                return null;
            }

            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() {
                return null;
            }

            @Override
            public Object unwrap(Class aClass) {
                return null;
            }
        };
        Set<ConstraintViolation<CustomerRequest>> violations = new HashSet<>();
        violations.add(constraintViolation);
        when(validator.validate(customerRequest)).thenReturn(violations);
        validationService.validateObject(customerRequest);

    }

}