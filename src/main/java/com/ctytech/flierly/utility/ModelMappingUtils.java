package com.ctytech.flierly.utility;


import org.modelmapper.Condition;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ModelMappingUtils {

    public Condition<String, String[]> canInclude(String fieldName, String... includes) {
        return (mappingContext) -> {
            // if includes is null or includes length = 0 return false
            if (includes == null || includes.length == 0) return false;
            // Check if fieldName is included in includes (case-insensitive)
            return Arrays.stream(includes).anyMatch(include -> include.equalsIgnoreCase(fieldName));
        };
    }

}
