package com.anji.springbootannotationlog.annotation;

import java.lang.annotation.*;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 15:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface SystemServiceLog {
    String description() default "";
}
