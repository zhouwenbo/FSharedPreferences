package com.fheebiy.fsp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.fheebiy.fsp.util.ValueOfUtils;


/**
 * Created on 2018/7/27.
 *
 * @author bob zhou.
 * Description: 跨进程的sp读写
 */
public class PrefContentProvider extends ContentProvider {

    public static final String PATH_STRING = "string";
    public static final String PATH_INT = "int";
    public static final String PATH_LONG = "long";
    public static final String PATH_BOOLEAN = "boolean";
    public static final String PATH_FLOAT = "float";

    private static final int CODE_STRING = 1;
    private static final int CODE_INT = 2;
    private static final int CODE_LONG = 3;
    private static final int CODE_BOOLEAN = 4;
    private static final int CODE_FLOAT = 5;

    private static final String[] COLUMNS = new String[]{"value"};

    public static final String SUFFIX_AUTH = ".ser_provider";

    private UriMatcher mUriMatcher;

    @Override
    public boolean onCreate() {
        String authority = getContext().getPackageName() + SUFFIX_AUTH;
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(authority, PATH_STRING, CODE_STRING);
        mUriMatcher.addURI(authority, PATH_INT, CODE_INT);
        mUriMatcher.addURI(authority, PATH_LONG, CODE_LONG);
        mUriMatcher.addURI(authority, PATH_BOOLEAN, CODE_BOOLEAN);
        mUriMatcher.addURI(authority, PATH_FLOAT, CODE_FLOAT);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String name = uri.getQueryParameter("name");
        String key = uri.getQueryParameter("key");
        String defValue = uri.getQueryParameter("defValue");

        MatrixCursor cursor = new MatrixCursor(COLUMNS, 1);
        switch (mUriMatcher.match(uri)) {
            case CODE_STRING:
                String stringResult = SpHelper.get(FspManager.getInstance().getAppContext(), name).getString(key, defValue);
                cursor.newRow().add(stringResult);
                break;
            case CODE_INT:
                int intResult = SpHelper.get(FspManager.getInstance().getAppContext(), name).getInt(key, ValueOfUtils.stringToInt(defValue));
                cursor.newRow().add(intResult);
                break;
            case CODE_LONG:
                long longResult = SpHelper.get(FspManager.getInstance().getAppContext(), name).getLong(key, ValueOfUtils.stringToLong(defValue));
                cursor.newRow().add(longResult);
                break;
            case CODE_BOOLEAN:
                boolean booleanResult = SpHelper.get(FspManager.getInstance().getAppContext(), name).getBoolean(key, ValueOfUtils.stringToBoolean(defValue));
                cursor.newRow().add(booleanResult + "");
                break;
            case CODE_FLOAT:
                float floatResult = SpHelper.get(FspManager.getInstance().getAppContext(), name).getFloat(key, ValueOfUtils.stringToFloat(defValue));
                cursor.newRow().add(floatResult);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        String name = uri.getQueryParameter("name");
        String key = uri.getQueryParameter("key");

        switch (mUriMatcher.match(uri)) {
            case CODE_STRING:
                SpHelper.get(FspManager.getInstance().getAppContext(), name).putString(key, values.getAsString("value"));
                break;
            case CODE_INT:
                SpHelper.get(FspManager.getInstance().getAppContext(), name).putInt(key, values.getAsInteger("value"));
                break;
            case CODE_LONG:
                SpHelper.get(FspManager.getInstance().getAppContext(), name).pubLong(key, values.getAsLong("value"));
                break;
            case CODE_BOOLEAN:
                SpHelper.get(FspManager.getInstance().getAppContext(), name).putBoolean(key, values.getAsBoolean("value"));
                break;
            case CODE_FLOAT:
                SpHelper.get(FspManager.getInstance().getAppContext(), name).putFloat(key, values.getAsFloat("value"));
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String name = uri.getQueryParameter("name");
        String key = uri.getQueryParameter("key");
        SpHelper.get(FspManager.getInstance().getAppContext(), name).remove(key);
        return 1;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
