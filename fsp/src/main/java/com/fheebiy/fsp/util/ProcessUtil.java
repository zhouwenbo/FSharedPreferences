package com.fheebiy.fsp.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.List;

/**
 * /**
 * Created on 2018/7/27.
 *
 * @author bob zhou.
 * Description: 进程相关工具类
 */
public class ProcessUtil {

    private static String currentProcessName;

    private static String packageName;


    private static String getCurrentPackageName(Context context) {
        if (TextUtils.isEmpty(packageName)) {
            packageName = context.getPackageName();
        }
        return packageName;
    }

    public static String getCurrentProcessName(Context context) {
        if (TextUtils.isEmpty(currentProcessName)) {
            currentProcessName = getCurrentProcessNameReal(context);
        }
        return currentProcessName;
    }

    public static void killSelf() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static boolean isMainProcess(Context context) {
        getCurrentProcessName(context);
        getCurrentPackageName(context);
        return currentProcessName == null || currentProcessName.equalsIgnoreCase(packageName);
    }

    private static String getCurrentProcessNameReal(Context context) {
        String currentProcessName = getCurrentProcessNameByReflect();
        if (TextUtils.isEmpty(currentProcessName)) {
            currentProcessName = getCurrentProcessNameByFile();
        }
        if (TextUtils.isEmpty(currentProcessName)) {
            currentProcessName = getCurrentProcessNameByPid(context);
        }
        return currentProcessName;
    }

    private static String getCurrentProcessNameByReflect() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                Class clazz = Class.forName("android.app.ActivityThread");
                Method tCurrentActivityThreadMethod = clazz.getDeclaredMethod("currentActivityThread");
                tCurrentActivityThreadMethod.setAccessible(true);
                Object tCurrentActivityThread = tCurrentActivityThreadMethod.invoke(null);

                Method tGetProcessNameMethod = clazz.getDeclaredMethod("getProcessName");
                tGetProcessNameMethod.setAccessible(true);
                return (String) tGetProcessNameMethod.invoke(tCurrentActivityThread);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getCurrentProcessNameByPid(Context context) {
        try {
            int pid = android.os.Process.myPid();
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
            if (infos != null) {
                for (ActivityManager.RunningAppProcessInfo processInfo : infos) {
                    if (processInfo.pid == pid) {
                        return processInfo.processName;
                    }
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "";
    }

    private static String getCurrentProcessNameByFile() {
        BufferedReader reader = null;
        try {
            final File cmdline = new File("/proc/" + android.os.Process.myPid() + "/cmdline");
            reader = new BufferedReader(new FileReader(cmdline));
            String processNameLine = reader.readLine();
            String pureProcessName = processNameLine.replaceAll("[^0-9a-zA-Z.-_+:]+", "").replace("\n", "");
            return pureProcessName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return "";
    }


}
