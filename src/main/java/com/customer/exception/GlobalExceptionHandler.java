package com.customer.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException,
                                                            WebRequest request) {
        return new ResponseEntity<>(handleMessageError(badRequestException), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundtException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundtException notFoundtException,
                                                          WebRequest request) {
        return new ResponseEntity<>(handleMessageError(notFoundtException), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> unprocessableEntityException(UnprocessableEntityException e,
                                                               WebRequest request) {
        return new ResponseEntity<>(handleMessageError(e), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private Map<String, Object> handleMessageError(Exception e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        return body;
    }

}