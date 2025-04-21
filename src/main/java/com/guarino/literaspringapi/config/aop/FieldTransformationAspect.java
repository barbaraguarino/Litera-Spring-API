package com.guarino.literaspringapi.config.aop;

import com.guarino.literaspringapi.shared.validation.TrimOnly;
import com.guarino.literaspringapi.shared.validation.UpperTrim;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class FieldTransformationAspect {

    private static final Logger logger = Logger.getLogger(FieldTransformationAspect.class.getName());

    @Pointcut("execution(* com.guarino.literaspringapi.application..*Mapper.toEntity(..))")
    public void mapperMethods() {}

    @Before("mapperMethods()")
    public void transformFields(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                transformFields(arg);
            }
        }
    }

    private void transformFields(Object obj) {
        if (obj == null) return;

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                transformStringField(obj, field);
            }
        }
    }

    private void transformStringField(Object obj, Field field) {
        field.setAccessible(true);
        try {
            String value = (String) field.get(obj);
            if (value != null && !value.trim().isEmpty()) {
                String transformed = applyTransformation(field, value);
                if (!transformed.equals(value)) {
                    field.set(obj, transformed);
                }
            }
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "Erro ao acessar o campo: " + obj.getClass().getSimpleName() + "." + field.getName(), e);
        }
    }

    private String applyTransformation(Field field, String value) {
        if (field.isAnnotationPresent(UpperTrim.class)) {
            return value.trim().toUpperCase();
        } else if (field.isAnnotationPresent(TrimOnly.class)) {
            return value.trim();
        } else {
            return value;
        }
    }
}

