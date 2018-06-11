package com.anji.springbootannotationlog.annotation;

import java.lang.annotation.*;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 15:03
 */
@Retention(RetentionPolicy.RUNTIME)         //元注解，定义注解被保留策略，一般有三种策略，
                                            // RetentionPolicy.SOURCE、
                                            // RetentionPolicy.CLASS、
                                            //RetentionPolicy.RUNTIME
@Target({ElementType.METHOD})               //定义了注解声明在哪些元素之前
@Documented
public @interface SystemControllerLog {
    //定义成员
    String description() default "";        //描述
    String actionType() default "";         //操作的类型，1、添加 2、修改 3、删除 4、查询
}
