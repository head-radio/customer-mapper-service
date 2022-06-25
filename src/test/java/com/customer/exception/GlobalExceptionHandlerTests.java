package com.customer.exception;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTests {

    @InjectMocks
    private GlobalExceptionHandler handler;

    private final String errorMessage = "error";
    private final String key = "message";

    @Test
    public void notFoundExceptionTest() throws Exception {
        ResponseEntity<Object> handled = handler.handleNotFoundException(new NotFoundtException(errorMessage), null);
        assertEquals(errorMessage, ((LinkedHashMap) handled.getBody()).get(key));
    }

    @Test
    public void badRequestExceptionTest() throws Exception {
        ResponseEntity<Object> handled = handler.handleBadRequestException(new BadRequestException(errorMessage), null);
        assertEquals(errorMessage, ((LinkedHashMap) handled.getBody()).get(key));
    }

    @Test
    public void unprocessableEntityExceptionTest() throws Exception {
        ResponseEntity<Object> handled = handler.unprocessableEntityException(new UnprocessableEntityException(errorMessage), null);
        assertEquals(errorMessage, ((LinkedHashMap) handled.getBody()).get(key));
    }

}
