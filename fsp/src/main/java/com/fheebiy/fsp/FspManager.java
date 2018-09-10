package com.fheebiy.fsp;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.fheebiy.fsp.util.ProcessUtil;


/**
 * Created on 2018/4/25.
 *
 * @author bob zhou.
 * Description: sp工具类
 */
public class FspManager {

    private CfgPrefProxy mCfgPrefProxy = new CfgPrefProxy();
    private static FspManager mInstance;
    private Context mContext;
    private boolean mIsMainProcess;
    private String mDefaultSpFile;

    private FspManager() {
    }

    private CfgPrefProxy getProxy() {
        return mCfgPrefProxy;
    }

    public static FspManager getInstance() {
        if (mInstance == null) {
            synchronized (FspManager.class) {
                if (mInstance == null) {
                    mInstance = new FspManager();
                }
            }
        }
        return mInstance;
    }

    public static <T> T getAppPref(Class<T> cls) {
        return getInstance().getProxy().create(cls);
    }

    public void init(Context context, String dfSpFileName) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null!");
        }

        if (!(context instanceof Application)) {
            throw new IllegalArgumentException("Context must Application!!!");
        }

        if (TextUtils.isEmpty(dfSpFileName)) {
            throw new IllegalArgumentException("Default sp file must not empty!");
        }

        mDefaultSpFile = dfSpFileName;
        mContext = context;
        mIsMainProcess = ProcessUtil.isMainProcess(context);
    }

    public Context getAppContext() {
        return mContext;
    }

    public boolean isMainProcess() {
        return mIsMainProcess;
    }

    public String getDefaultSpFile() {
        return mDefaultSpFile;
    }
}
