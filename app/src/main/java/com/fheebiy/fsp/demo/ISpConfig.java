package com.fheebiy.fsp.demo;


import com.fheebiy.fsp.config.Call;
import com.fheebiy.fsp.config.DEFAULT_INT;
import com.fheebiy.fsp.config.DEFAULT_STRING;
import com.fheebiy.fsp.config.KEY;
import com.fheebiy.fsp.config.SP_FILE;

/**
 * Created on 2018/9/5.
 *
 * @author bob zhou.
 * Description:
 */
public interface ISpConfig {

    @KEY("key_user_id")
    @DEFAULT_STRING("bob zhou")
    Call<String> userId();

    @KEY("key_index")
    @DEFAULT_INT(-1)
    @SP_FILE("test_sp_file")
    Call<Integer> index();

    @KEY("key_success")
    Call<Boolean> isSuccess();

    @KEY("key_price")
    Call<Float> price();

    @KEY("key_time")
    Call<Long> time();

}
