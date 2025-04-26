package com.guarino.literaspringapi.shared.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
