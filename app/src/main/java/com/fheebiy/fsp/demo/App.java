package com.fheebiy.fsp.demo;

import android.app.Application;

import com.fheebiy.fsp.FspManager;


/**
 * Created on 2018/9/5.
 *
 * @author bob zhou.
 * Description: app
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FspManager.getInstance().init(this, "zsp_sp_file");
    }
}
