package com.fheebiy.fsp.config;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 2018/9/5.
 *
 * @author bob zhou.
 * Description: 配置SP文件名称
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface SP_FILE {
    String value() default "";
}
