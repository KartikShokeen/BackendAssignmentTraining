package com.BackendMiniProject.demo.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
@NoArgsConstructor
public class InvalidOrderException extends RuntimeException {
    private String message;
    public InvalidOrderException(String message) {
        super(message);
        this.message=message;
    }
}
