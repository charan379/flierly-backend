package com.ctytech.flierly.config;

import org.modelmapper.ModelMapper;

import java.util.Arrays;

public class CustomModelMapper extends ModelMapper {

    public boolean canInclude(String fieldName, String... includes) {
        // if includes is null or includes length = 0 return false
        if (includes == null || includes.length == 0) return false;
        // Check if fieldName is included in includes (case-insensitive)
        return Arrays.stream(includes).anyMatch(include -> include.equalsIgnoreCase(fieldName));
    }
}
