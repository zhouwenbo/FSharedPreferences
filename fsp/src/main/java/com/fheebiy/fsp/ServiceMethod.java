package com.fheebiy.fsp;


import com.fheebiy.fsp.config.DEFAULT_BOOLEAN;
import com.fheebiy.fsp.config.DEFAULT_FLOAT;
import com.fheebiy.fsp.config.DEFAULT_INT;
import com.fheebiy.fsp.config.DEFAULT_LONG;
import com.fheebiy.fsp.config.DEFAULT_STRING;
import com.fheebiy.fsp.config.KEY;
import com.fheebiy.fsp.config.SP_FILE;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created on 2018/4/25.
 *
 * @author bob zhou.
 * Description: 模仿retrofit.
 */
public class ServiceMethod<T> {

    private String mKey;
    private String mSpFile;
    private Object mDefault;
    private final Class<T> mTypeClass;

    private ServiceMethod(Builder<T> builder) {
        mKey = builder.mKey;
        mSpFile = builder.mSpFile;
        mDefault = builder.mDefault;
        mTypeClass = builder.mTypeClass;
    }

    public String getKey() {
        return mKey;
    }

    public String getSpFile() {
        return mSpFile;
    }

    public Object getDefault() {
        return mDefault;
    }

    public Class<T> getTypeClass() {
        return mTypeClass;
    }

    public static final class Builder<T> {
        final Method method;
        final Annotation[] methodAnnotations;

        String mKey;
        private String mSpFile;
        Object mDefault;
        final Class<T> mTypeClass;

        public Builder(Method method) {
            this.method = method;
            this.methodAnnotations = method.getAnnotations();

            ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
            Type[] types = returnType.getActualTypeArguments();
            mTypeClass = (Class<T>) types[0];
        }

        public ServiceMethod build() {
            for (Annotation annotation : methodAnnotations) {
                parseMethodAnnotation(annotation);
            }

            return new ServiceMethod<>(this);
        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof KEY) {
                mKey = ((KEY) annotation).value();
            } else if (annotation instanceof DEFAULT_STRING) {
                mDefault = ((DEFAULT_STRING) annotation).value();
            } else if (annotation instanceof DEFAULT_BOOLEAN) {
                mDefault = ((DEFAULT_BOOLEAN) annotation).value();
            } else if (annotation instanceof DEFAULT_FLOAT) {
                mDefault = ((DEFAULT_FLOAT) annotation).value();
            } else if (annotation instanceof DEFAULT_INT) {
                mDefault = ((DEFAULT_INT) annotation).value();
            } else if (annotation instanceof DEFAULT_LONG) {
                mDefault = ((DEFAULT_LONG) annotation).value();
            } else if (annotation instanceof SP_FILE) {
                mSpFile = ((SP_FILE) annotation).value();
            }
        }
    }
}
