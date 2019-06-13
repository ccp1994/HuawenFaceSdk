package com.fpa.mainsupport.core.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;


import com.huawen.huawenface.BuildConfig;

import java.io.File;

public class VersionControler {
    static String versionCodeUrl = "http://ccpwj1994.eicp.net:8001/download/update.html";
    public static final String tag = VersionControler.class.getSimpleName();
    //private static SharedPreference sp;

    static {
        //   sp = new SharedPreference(Constants.sp);
    }
    // 安装程序
    public static void install(Context context, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        File apkFile=new File(apkPath);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context,
                    BuildConfig.APPLICATION_ID + ".fileprovider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }


    /**
     * @return
     */
    public static int getCurVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            Log.e(tag, e.toString());
            return 0;
        }
    }
    /**
     *
     */
    public static String getCurVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            Log.e(tag, e.toString());
            return "0";
        }
    }

    public static boolean isAPI19() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT;
    }

    /**
     * get Meta-data from Manifest
     * @param context
     * @param metaDataName
     * @return
     */
    public static String getMetaData(Context context, String metaDataName) {
            String channel = "";
            try {
                channel = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString(metaDataName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return channel;
    }
}
