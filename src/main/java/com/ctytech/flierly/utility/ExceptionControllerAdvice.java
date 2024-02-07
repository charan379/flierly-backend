package com.ctytech.flierly.utility;

import com.ctytech.flierly.FlierlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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

    private String getExceptionClassName(Exception e) {

        try {
         String[] names = e.getClass().getName().split("\\.");
         return names[names.length -1];

        } catch (Exception exception) {
            return exception.getClass().getName();
        }

    }

}
