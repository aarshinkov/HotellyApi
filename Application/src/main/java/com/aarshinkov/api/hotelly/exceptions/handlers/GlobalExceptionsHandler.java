package com.aarshinkov.api.hotelly.exceptions.handlers;

import java.time.LocalDateTime;

import com.aarshinkov.api.hotelly.errors.ErrorResponse;
import com.aarshinkov.api.hotelly.exceptions.HollException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionsHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(HollException.class)
    public ResponseEntity<ErrorResponse> errorHandler(HollException ex, WebRequest request) {
        ErrorResponse errors = new ErrorResponse();
        errors.setMessage(ex.getMessage());
        errors.setCode(ex.getCode());
        errors.setDetails(ex.getDetails());
        errors.setTimestamp(LocalDateTime.now());

        log.error("Error: " + errors.toString());

        return new ResponseEntity<>(errors, ex.getStatus());
    }

}
