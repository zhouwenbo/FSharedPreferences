package com.fheebiy.fsp;


import android.text.TextUtils;

import com.fheebiy.fsp.config.Call;


/**
 * Created on 2018/4/25.
 *
 * @author bob zhou.
 * Description:根据进程来判断执行get, put, remove方法。
 * 如果是主进程则则直接调用sp方法
 * 如果是非主进程则通过ContentProvider来中转
 */
public class SharedPrefCall<T> implements Call<T> {

    private final ServiceMethod<T> serviceMethod;

    public SharedPrefCall(ServiceMethod<T> serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    @Override
    public T get() {
        String key = serviceMethod.getKey();
        Object defaultValue = serviceMethod.getDefault();
        Class<T> cls = serviceMethod.getTypeClass();
        String spName = serviceMethod.getSpFile();
        if (TextUtils.isEmpty(spName)) {
            spName = FspManager.getInstance().getDefaultSpFile();
        }

        try {
            if (cls == Integer.class) {
                return cls.cast(getIntValue(spName, key, defaultValue == null ? 0 : (int) defaultValue));
            } else if (cls == Float.class) {
                return cls.cast(getFloatValue(spName, key, defaultValue == null ? 0f : (float) defaultValue));
            } else if (cls == Boolean.class) {
                return cls.cast(getBooleanValue(spName, key, defaultValue != null && (boolean) defaultValue));
            } else if (cls == Long.class) {
                return cls.cast(getLongValue(spName, key, defaultValue == null ? 0L : (long) defaultValue));
            } else if (cls == String.class) {
                return cls.cast(getStringValue(spName, key, defaultValue == null ? "" : (String) defaultValue));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(T t) {
        String key = serviceMethod.getKey();
        Class cls = serviceMethod.getTypeClass();
        String spName = serviceMethod.getSpFile();
        if (TextUtils.isEmpty(spName)) {
            spName = FspManager.getInstance().getDefaultSpFile();
        }

        try {
            if (cls == Integer.class) {
                putIntValue(spName, key, (Integer) t);
            } else if (cls == Float.class) {
                putFloatValue(spName, key, (Float) t);
            } else if (cls == Boolean.class) {
                putBooleanValue(spName, key, (Boolean) t);
            } else if (cls == Long.class) {
                putLongValue(spName, key, (Long) t);
            } else if (cls == String.class) {
                putStringValue(spName, key, (String) t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove() {
        String key = serviceMethod.getKey();
        String spName = serviceMethod.getSpFile();
        if (TextUtils.isEmpty(spName)) {
            spName = FspManager.getInstance().getDefaultSpFile();
        }
        if (FspManager.getInstance().isMainProcess()) {
            SpHelper.get(FspManager.getInstance().getAppContext(), spName).remove(key);
        } else {
            ProcessSafePref.get(FspManager.getInstance().getAppContext(), spName).remove(key);
        }
    }

    private String getStringValue(String spFile, String key, String defValue) {
        if (FspManager.getInstance().isMainProcess()) {
            return SpHelper.get(FspManager.getInstance().getAppContext(), spFile).getString(key, defValue);
        } else {
            return ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).getString(key, defValue);
        }
    }

    private int getIntValue(String spFile, String key, int defValue) {
        if (FspManager.getInstance().isMainProcess()) {
            return SpHelper.get(FspManager.getInstance().getAppContext(), spFile).getInt(key, defValue);
        } else {
            return ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).getInt(key, defValue);
        }
    }

    private boolean getBooleanValue(String spFile, String key, boolean defValue) {
        if (FspManager.getInstance().isMainProcess()) {
            return SpHelper.get(FspManager.getInstance().getAppContext(), spFile).getBoolean(key, defValue);
        } else {
            return ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).getBoolean(key, defValue);
        }

    }

    private long getLongValue(String spFile, String key, long defValue) {
        if (FspManager.getInstance().isMainProcess()) {
            return SpHelper.get(FspManager.getInstance().getAppContext(), spFile).getLong(key, defValue);
        } else {
            return ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).getLong(key, defValue);
        }
    }

    private float getFloatValue(String spFile, String key, float defValue) {
        if (FspManager.getInstance().isMainProcess()) {
            return SpHelper.get(FspManager.getInstance().getAppContext(), spFile).getFloat(key, defValue);
        } else {
            return ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).getFloat(key, defValue);
        }
    }

    private void putStringValue(String spFile, String key, String value) {
        if (FspManager.getInstance().isMainProcess()) {
            SpHelper.get(FspManager.getInstance().getAppContext(), spFile).putString(key, value);
        } else {
            ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).putString(key, value);
        }
    }

    private void putIntValue(String spFile, String key, int value) {
        if (FspManager.getInstance().isMainProcess()) {
            SpHelper.get(FspManager.getInstance().getAppContext(), spFile).putInt(key, value);
        } else {
            ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).putInt(key, value);
        }
    }

    private void putBooleanValue(String spFile, String key, boolean value) {
        if (FspManager.getInstance().isMainProcess()) {
            SpHelper.get(FspManager.getInstance().getAppContext(), spFile).putBoolean(key, value);
        } else {
            ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).putBoolean(key, value);
        }
    }

    private void putLongValue(String spFile, String key, long value) {
        if (FspManager.getInstance().isMainProcess()) {
            SpHelper.get(FspManager.getInstance().getAppContext(), spFile).pubLong(key, value);
        } else {
            ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).putLong(key, value);
        }
    }

    private void putFloatValue(String spFile, String key, float value) {
        if (FspManager.getInstance().isMainProcess()) {
            SpHelper.get(FspManager.getInstance().getAppContext(), spFile).putFloat(key, value);
        } else {
            ProcessSafePref.get(FspManager.getInstance().getAppContext(), spFile).putFloat(key, value);
        }
    }
}
