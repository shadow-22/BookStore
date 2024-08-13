package com.example.filters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  // The annotation will be available at runtime
@Target({ElementType.METHOD, ElementType.TYPE})  // The annotation can be applied to methods and classes
public @interface JWTRequired {
}
