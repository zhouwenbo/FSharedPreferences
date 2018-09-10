package com.fheebiy.fsp.util;

/**
 * ValueOfUtils
 * Created by tianbin on 2018/1/31.
 */
public final class ValueOfUtils {

    private ValueOfUtils() {
    }

    public static double stringToDouble(String str) {
        double value = 0;
        try {
            value = Double.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int stringToInt(String str) {
        int value = 0;
        try {
            value = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static float stringToFloat(String str) {
        float value = 0;
        try {
            value = Float.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static long stringToLong(String str) {
        long value = 0;
        try {
            value = Long.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static boolean stringToBoolean(String str) {
        return Boolean.parseBoolean(str);
    }

}

