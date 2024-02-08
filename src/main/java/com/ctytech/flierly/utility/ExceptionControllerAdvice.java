package com.ctytech.flierly.utility;

import com.ctytech.flierly.FlierlyException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private Environment environment;

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {

        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setException(getExceptionClassName(exception));
        errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION"));
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FlierlyException.class)
    public ResponseEntity<ErrorInfo> flierlyExceptionHandler(FlierlyException flierlyException) {


        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setException(getExceptionClassName(flierlyException));
        errorInfo.setErrorMessage(environment.getProperty(flierlyException.getMessage()));
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> methodArgNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        String errorMessage = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setException(getExceptionClassName(exception));
        errorInfo.setErrorMessage(errorMessage);
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> constraintViolationExceptionHandler(ConstraintViolationException exception) {

        String errorMessage = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));

        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setException(getExceptionClassName(exception));
        errorInfo.setErrorMessage(errorMessage);
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    private String getExceptionClassName(Exception e) {

        try {
            String[] names = e.getClass().getName().split("\\.");
            return names[names.length - 1];

        } catch (Exception exception) {
            return exception.getClass().getName();
        }

    }

}
