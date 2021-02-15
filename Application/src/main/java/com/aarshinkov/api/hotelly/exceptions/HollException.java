package com.aarshinkov.api.hotelly.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HollException extends RuntimeException {

    private Integer code;
    private String message;
    private String details;
    private HttpStatus status;

    public HollException(String message) {
        super(message);
    }
}
