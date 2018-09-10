package com.fheebiy.fsp.demo;


import com.fheebiy.fsp.config.Call;
import com.fheebiy.fsp.config.DEFAULT_INT;
import com.fheebiy.fsp.config.DEFAULT_STRING;
import com.fheebiy.fsp.config.KEY;

/**
 * Created on 2018/9/5.
 *
 * @author bob zhou.
 * Description:
 */
public interface ISpConfig {

    @KEY("key_user_id")
    @DEFAULT_STRING("bob zhou")
    Call<String> getUserId();

    @KEY("key_index")
    @DEFAULT_INT(-1)
    Call<Integer> getIndex();

    @KEY("key_success")
    Call<Boolean> isSuccess();

    @KEY("key_price")
    Call<Float> getPrice();

    @KEY("key_time")
    Call<Long> getTime();

}
