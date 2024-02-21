package com.ctytech.flierly.utility;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;


@Getter
@Setter
@EqualsAndHashCode
public class ErrorInfo implements Serializable {

    private String exception;

    private String errorMessage;

    private Integer errorCode;

    private LocalDateTime timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, StackTraceElement[]> stackTrace;

}
