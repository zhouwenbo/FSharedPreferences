package com.fheebiy.fsp;


import android.util.ArrayMap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Created on 2018/4/25.
 *
 * @author bob zhou.
 * Description:
 */
public class CfgPrefProxy {

    private final ArrayMap<Method, ServiceMethod> mServiceMethodCache = new ArrayMap<>();

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {
                        if (method.getDeclaringClass() == Object.class) {
                            return method.invoke(this, args);
                        }
                        ServiceMethod serviceMethod = loadServiceMethod(method);
                        return new SharedPrefCall<>(serviceMethod);
                    }
                });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod result;
        synchronized (mServiceMethodCache) {
            result = mServiceMethodCache.get(method);
            if (result == null) {
                result = new ServiceMethod.Builder(method).build();
                mServiceMethodCache.put(method, result);
            }
        }
        return result;
    }
}
