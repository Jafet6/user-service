package com.accenture.userservice.error.Details;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected HttpStatus status;
    protected String message;
}
