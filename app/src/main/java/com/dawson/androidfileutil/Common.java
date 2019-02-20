package com.dawson.androidfileutil;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jennifer on 2015/4/22.
 */
public class Common {
    private static final String SPOTIFY_PACKAGENAME = "com.spotify.music";
    private static final String SPOTIFY_LAUNCHER = "com.spotify.music.MainActivity";
    private static final String ALEXA_PACKAGENAME = "com.amazon.dee.app";
    private static final String ALEXA_LAUNCHER = "com.amazon.dee.webapp.activity.AlexaWebAppActivity";

    private static boolean checkSpecifyProgram(Context context) {
        boolean flag = false;
        if (context == null) {
            return flag;
        }
        PackageManager manager = context.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> apps = manager.queryIntentActivities(mainIntent, 0);
        for (ResolveInfo app : apps) {
            String pkg = app.activityInfo.packageName;
            if (pkg.equals(SPOTIFY_PACKAGENAME)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void triggerSpotifyClient(Context context, String uri) {
        if (checkSpecifyProgram(context)) {
            if (uri.isEmpty()) {
                launchApp(context, SPOTIFY_PACKAGENAME, SPOTIFY_LAUNCHER);
            } else {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(browserIntent);
            }
        } else {
            accessGoogleAppStore(context, SPOTIFY_PACKAGENAME);
        }
    }

    public static void triggerAlexaClient(Context context) {
        if (hasInstallApp(context, ALEXA_PACKAGENAME)) {
            launchApp(context, ALEXA_PACKAGENAME, ALEXA_LAUNCHER);
        } else {
            accessGoogleAppStore(context, ALEXA_PACKAGENAME);
        }
    }

    public static void launchApp(Context context, String packageName, String launcher) {
        Intent intent;
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName(packageName, launcher);
        intent.setComponent(cn);
        context.startActivity(intent);
    }

    private static void accessGoogleAppStore(Context context, String packagename) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id="
                + packagename)));
    }

    public static boolean checkEmail(String email) {
        boolean flag;
        try {
//            String check = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|( ([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            //don't do strict checking because audiogum will check.
            String check2 = "^(\\S+)@(\\S+)\\.(\\S+)$";
            Pattern regex = Pattern.compile(check2);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static int getImageResourceIdByResourceName(Context context, String resourcePrefix, int i) {
        String resourceId;
        if (i < 10) {
            resourceId = resourcePrefix + "0" + i;
        } else {
            resourceId = resourcePrefix + i;
        }
        return context.getResources().getIdentifier(resourceId, "drawable", context.getPackageName());
    }

    //for the battery dot picture count is from 01 to 34, while the batteryLevel is from 0 to 100
    //the value returned is 4,7,10,13.....,34, the corresponding dot count in he battery dot picture is 0,1,2,3..,10
    public static int generateIndexByBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 95) {
            return 34;
        } else {
            return (batteryLevel / 10 + 1) * 3 + 1;
        }
    }

    public static String getVerName(MyApplication context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "error";
        }
        if (pi == null) {
            return "error1";
        }
        String versionName = pi.versionName;
        if (versionName == null) {
            return "not set";
        }
        return versionName;
    }

    public static String getVerCode(MyApplication context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "error";
        }
        if (pi == null) {
            return "error1";
        }
        int versionCode = pi.versionCode;

        return String.valueOf(versionCode);
    }

    public static boolean pictureUrlIsRight(String srcCoverUrl) {
        return (!TextUtils.isEmpty(srcCoverUrl)) && (srcCoverUrl.contains(".jpg") || srcCoverUrl.contains(".png")
                || srcCoverUrl.contains(".JPG") || srcCoverUrl.contains(".PNG"));
    }

    //add for checking whether current view is shown just because view.show() is not so accurate.
    //code come from net work.
    public static boolean isViewCovered(final View view) {
        View currentView = view;

        Rect currentViewRect = new Rect();
        boolean partVisible = currentView.getGlobalVisibleRect(currentViewRect);
        boolean totalHeightVisible = (currentViewRect.bottom - currentViewRect.top) >= currentView.getMeasuredHeight();
        boolean totalWidthVisible = (currentViewRect.right - currentViewRect.left) >= currentView.getMeasuredWidth();
        boolean totalViewVisible = partVisible && totalHeightVisible && totalWidthVisible;
        //if any part of the view is clipped by any of its parents,return true
        if (!totalViewVisible) {
            return true;
        }

        while (currentView.getParent() instanceof ViewGroup) {
            ViewGroup currentParent = (ViewGroup) currentView.getParent();
            //if the parent of view is not visible,return true
            if (currentParent.getVisibility() != View.VISIBLE) {
                return true;
            }

            int start = indexOfViewInParent(currentView, currentParent);
            for (int i = start + 1; i < currentParent.getChildCount(); i++) {
                Rect viewRect = new Rect();
                view.getGlobalVisibleRect(viewRect);
                View otherView = currentParent.getChildAt(i);
                Rect otherViewRect = new Rect();
                otherView.getGlobalVisibleRect(otherViewRect);
                //if view intersects its older brother(covered),return true
                if (Rect.intersects(viewRect, otherViewRect)) {
                    return true;
                }
            }
            currentView = currentParent;
        }
        return false;
    }


    private static int indexOfViewInParent(View view, ViewGroup parent) {
        int index;
        for (index = 0; index < parent.getChildCount(); index++) {
            if (parent.getChildAt(index) == view) {
                break;
            }
        }
        return index;
    }

    public static boolean hasInstallApp(Context context, String pakageName) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(pakageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAttachedActivityDestroyed(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        return activity == null || activity.isFinishing();
    }

    public static boolean needConsiderExpired(String service) {
        return service.equals(Constants.CHANNEL.TIDAL)
                || service.equals(Constants.CHANNEL.AUDIOGUM_TIDAL)
                || service.equals(Constants.CHANNEL.NAPSTER)
                || service.equals(Constants.CHANNEL.AUDIOGUM_NAPSTER);
    }

    @Nullable
    public static Spannable updateTradeMarkStyle(String srcString, int srcFontSize) {
        Spannable retSpan = null;
        String fondString = "®";
        if (TextUtils.isEmpty(srcString)) {
            return retSpan;
        }
        if (srcString.contains(fondString)) {
            retSpan = new SpannableString(srcString);
            String tempSrcSting = srcString;
            int newStartIndex;
            int newEndIndex;
            int oldEndIndex = 0;
            while (tempSrcSting.contains(fondString)) {
                //get location of trade mark
                newStartIndex = tempSrcSting.indexOf(fondString) + oldEndIndex;
                newEndIndex = tempSrcSting.indexOf(fondString) + fondString.length() + oldEndIndex;
                //adjust mark's size and change it to more upper.
                retSpan.setSpan(new TextAppearanceSpan(null, 0, (srcFontSize > 0 ? srcFontSize * 2 / 3 : 10), null, null), (int) newStartIndex, (int) newEndIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                retSpan.setSpan(new SuperscriptSpan(), newStartIndex, newEndIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                //update src string for next check.
                tempSrcSting = srcString.substring(newEndIndex);
                oldEndIndex = newEndIndex;
            }
        }
        return retSpan;
    }
}
