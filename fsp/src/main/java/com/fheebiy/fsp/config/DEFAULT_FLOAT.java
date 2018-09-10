package com.fheebiy.fsp.config;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 2018/4/25.
 *
 * @author bob zhou.
 * Description:
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface DEFAULT_FLOAT {
    float value() default 0f;
}
