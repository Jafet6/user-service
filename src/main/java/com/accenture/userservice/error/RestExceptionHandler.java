package com.accenture.userservice.error;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.accenture.userservice.error.Details.ApiErrorDetails;
import com.accenture.userservice.error.Details.ExceptionDetails;
import com.accenture.userservice.error.Details.ValidationExceptionDetails;
import com.sun.istack.Nullable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException exception) {
        return new ResponseEntity<>(
                ApiErrorDetails.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message(exception.getMessage())
                        .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(
            EntityExistsException exception) {
        return new ResponseEntity<>(
                ApiErrorDetails.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(exception.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    protected ResponseEntity<Object> handleNoSuchAlgorithm(
            NoSuchAlgorithmException exception) {
        return new ResponseEntity<>(
                ApiErrorDetails.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(exception.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

//    @ExceptionHandler(InvalidPasswordException.class)
//    protected ResponseEntity<Object> handlePasswordNotMatching(
//            InvalidPasswordException exception) {
//        return new ResponseEntity<>(
//                ApiErrorDetails.builder()
//                        .status(HttpStatus.BAD_REQUEST)
//                        .message(exception.getMessage())
//                        .build(), HttpStatus.BAD_REQUEST
//        );
//    }

    @ExceptionHandler(JsonGenerationException.class)
    protected ResponseEntity<Object> handleJsonGenerationException(
            JsonGenerationException exception) {
        return new ResponseEntity<>(
                ApiErrorDetails.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(exception.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<Object> handleIOException(
            IOException exception) {
        return new ResponseEntity<>(
                ApiErrorDetails.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(exception.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(JsonMappingException.class)
    protected ResponseEntity<Object> handleJsonMappingException(
            JsonMappingException exception) {
        return new ResponseEntity<>(
                ApiErrorDetails.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(exception.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

//    @ExceptionHandler(InvalidTokenException.class)
//    protected ResponseEntity<ApiErrorDetails> handleInvalidTokenException(
//            InvalidTokenException exception) {
//        return new ResponseEntity<>(
//                ApiErrorDetails.builder()
//                        .status(HttpStatus.UNAUTHORIZED)
//                        .message(exception.getMessage())
//                        .build(), HttpStatus.UNAUTHORIZED
//        );
//    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    protected ResponseEntity<ApiErrorDetails> handleHttpClientErrorExceptionUnauthorized(
            HttpClientErrorException.Unauthorized exception) {
        return new ResponseEntity<>(
                ApiErrorDetails.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .message(exception.getStatusText())
                        .build(), HttpStatus.UNAUTHORIZED
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(exception.getMessage())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .status(status)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }

}

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        var bodies = new ArrayList<ResponseErrors.Body>();
//        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
//            String nome = ((FieldError) error).getField();
//            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
//            bodies.add(new ResponseErrors.Body(nome, message));
//        }
//
//        var errors = new ResponseErrors();
//        errors.setStatus(status.value());
//        errors.setMessage("Campos Invalidos");
//        errors.setData(OffsetDateTime.now());
//        errors.setBodys(bodies);
//
//        return super.handleExceptionInternal(ex, errors, headers, status, request);
//
//    }

