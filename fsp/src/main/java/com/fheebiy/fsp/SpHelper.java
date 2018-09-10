package com.fheebiy.fsp;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;


/**
 * Created on 2018/7/27.
 *
 * @author bob zhou.
 * Description: Sp工具类
 */
public class SpHelper {

    private static final Map<String, WeakReference<SpHelper>> SP_CACHE = new HashMap<>(2, 1);

    private SharedPreferences mSharedPreferences;

    public static SpHelper get(Context context, String name) {
        SpHelper prefs = null;
        synchronized (SpHelper.class) {
            if (SP_CACHE.containsKey(name) && SP_CACHE.get(name) != null) {
                prefs = SP_CACHE.get(name).get();
            }
            if (prefs == null) {
                prefs = new SpHelper(context, name);
                SP_CACHE.put(name, new WeakReference<>(prefs));
            }
        }
        return prefs;
    }

    private SpHelper(Context context, String name) {
        mSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void putBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public void pubLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).apply();
    }

    public void putFloat(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public void remove(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    public boolean contains(String key) {
        return mSharedPreferences.contains(key);
    }
}
