package com.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.FIELD)//作用在成员上
@Retention(RetentionPolicy.RUNTIME)//运行时保存
public @interface MyAnnotation {
    int min() default 1;
    int max() default 100;
    String message() default "secuss";

}
