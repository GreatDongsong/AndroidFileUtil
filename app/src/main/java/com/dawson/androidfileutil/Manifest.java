package com.dawson.androidfileutil;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


/**
 * Created by rick on 15-7-27.
 */
public class Manifest {

    public static String getVersion()//获取版本号
    {
        Context context = MyApplication.getContext();
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0.0";
        }
    }

    public static int getVersionCode()//获取版本号(内部识别号)
    {
        Context context = MyApplication.getContext();
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public static String getChannel() {
        String code = getMetaData("UMENG_CHANNEL");
        if (code != null) {
            return code;
        }
        return "Channel ID";
    }

    private static String getMetaData(String key) {
        Context context = MyApplication.getContext();
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            Object value = ai.metaData.get(key);
            if (value != null) {
                return value.toString();
            }
        } catch (Exception e) {
            //
        }
        return null;
    }

    public static String getAndroidUserAgent() {
        //Libratone-android / 3.6.7( 325 )
        return "Libratone-android / " + Manifest.getVersion() + "( " + Manifest.getVersionCode() + " )";
    }
}
