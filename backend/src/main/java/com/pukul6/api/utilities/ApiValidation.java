package com.pukul6.api.utilities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;

public class ApiValidation {
    public static Map<String, Object> getErrorMessages(Errors errors) {
        var result = new HashMap<String, Object>();
        for (var err : errors.getFieldErrors()) {
            result.put(err.getField(), err.getDefaultMessage());
        }
        return result;
    }
}
