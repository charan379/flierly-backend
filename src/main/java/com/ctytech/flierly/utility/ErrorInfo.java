package com.ctytech.flierly.utility;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter @Setter @EqualsAndHashCode @AllArgsConstructor
public class ErrorInfo implements Serializable {

    private String exception;

    private String errorMessage;

    private Integer errorCode;

    private LocalDateTime timestamp;

}
