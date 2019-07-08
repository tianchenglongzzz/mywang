package com.jhjz.emr.lstd_public.utils;

import android.util.Log;

import com.jhjz.emr.lstd_public.BuildConfig;

/**
 * 日志管理工具类(耦合度高,有改进空间)
 *
 * @author dzq
 */
public class LogUtil {
    private static boolean isPrintLog = BuildConfig.LOG_DEBUG;
    private static int LOG_MAXLENGTH = 2000;
    public static boolean isOpen_dataCheck=false;//控制输入内容检测是否开启
    public static boolean isOpenDebug = BuildConfig.LOG_DEBUG;
    public static boolean isOpenWarn = BuildConfig.LOG_DEBUG;

    public static void d(String tag, String msg) {
        if (isOpenDebug) {
            // Log.d(ManagerUtils.getCurrentClassName()+"_"+ManagerUtils.getCurrentMethodName()+"_"+tag, msg);
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isOpenWarn) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isOpenWarn) {
            Log.e(tag, msg);
        }
    }


    public static void server(String tag, String msg) {
        if (isOpenDebug) {
            Log.d("server_" + tag, msg);
        }
    }
    public static void dd(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.d(tagName + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.d(tagName + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }
}