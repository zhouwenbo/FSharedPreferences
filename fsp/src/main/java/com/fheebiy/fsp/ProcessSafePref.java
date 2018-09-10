package com.fheebiy.fsp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


/**
 * Created on 2018/7/27.
 *
 * @author bob zhou.
 * Description: 跨进程的sp读写
 */
public class ProcessSafePref {

    private ContentResolver mContentResolver;
    private String mAuthority;
    private String name;

    private ProcessSafePref(Context context, String name) {
        this.mContentResolver = context.getContentResolver();
        this.mAuthority = context.getPackageName() + PrefContentProvider.SUFFIX_AUTH;
        this.name = name;
    }

    public static ProcessSafePref get(Context context, String name) {
        return new ProcessSafePref(context, name);
    }

    public int getInt(String key, int defValue) {
        int result = defValue;
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_INT)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .appendQueryParameter("defValue", String.valueOf(defValue))
                .build();
        Cursor cursor = mContentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getInt(0);
            }
            cursor.close();
        }
        return result;
    }

    public void putInt(String key, int value) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_INT)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .build();
        try {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("value", value);
            mContentResolver.insert(uri, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getLong(String key, long defValue) {
        long result = defValue;
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_LONG)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .appendQueryParameter("defValue", String.valueOf(defValue))
                .build();
        Cursor cursor = mContentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getLong(0);
            }
            cursor.close();
        }
        return result;
    }

    public void putLong(String key, long value) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_LONG)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .build();
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("value", value);
        try {
            mContentResolver.insert(uri, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String key, String defValue) {
        String result = defValue;
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_STRING)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .appendQueryParameter("defValue", String.valueOf(defValue))
                .build();
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(uri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    result = cursor.getString(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public void putString(String key, String value) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_STRING)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .build();
        try {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("value", value);
            mContentResolver.insert(uri, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getBoolean(String key, boolean defValue) {
        boolean result = defValue;
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_BOOLEAN)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .appendQueryParameter("defValue", String.valueOf(defValue))
                .build();
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(uri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    String resultValue = cursor.getString(0);
                    result = Boolean.parseBoolean(resultValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }


    public void putBoolean(String key, boolean value) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_BOOLEAN)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .build();
        try {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("value", value);
            mContentResolver.insert(uri, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public float getFloat(String key, float defValue) {
        float result = defValue;
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_FLOAT)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .appendQueryParameter("defValue", String.valueOf(defValue))
                .build();
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(uri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    result = cursor.getFloat(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public void putFloat(String key, float value) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendPath(PrefContentProvider.PATH_FLOAT)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .build();
        try {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("value", value);
            mContentResolver.insert(uri, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String key) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(mAuthority)
                .appendQueryParameter("name", name)
                .appendQueryParameter("key", key)
                .build();
        mContentResolver.delete(uri, null, null);
    }
}

