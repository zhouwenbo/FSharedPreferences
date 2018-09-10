package com.fheebiy.fsp.config;

/**
 * Created on 2018/4/25.
 *
 * @author bob zhou.
 * Description:
 */
public interface Call<T> {

    T get();

    void put(T t);

    void remove();
}
