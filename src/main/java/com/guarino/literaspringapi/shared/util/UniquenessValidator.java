package com.guarino.literaspringapi.shared.util;

import com.guarino.literaspringapi.shared.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Predicate;

@Component
public class UniquenessValidator {

    public record ValidationEntry(Object value, Predicate<Object> existsFunction) {}

    public void validate(String entityName, Object value, Predicate<Object> existsFunction, String fieldName) {
        if (isNotEmpty(value) && existsFunction.test(value)) {
            throw new ResourceAlreadyExistsException(entityName, fieldName, value.toString());
        }
    }

    public void validateMultiple(String entityName, Map<String, ValidationEntry> fields) {
        for (Map.Entry<String, ValidationEntry> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            ValidationEntry validationEntry = entry.getValue();
            validate(entityName, validationEntry.value(), validationEntry.existsFunction(), fieldName);
        }
    }

    private boolean isNotEmpty(Object value) {
        if (value == null) return false;

        if (value instanceof String s) {
            return StringUtils.isNotBlank(s);
        }
        return true;
    }
}
