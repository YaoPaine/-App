package com.yao.resource.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 上午10:05
 * @Version:
 */

public class Utils {
    private Utils() {

    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        //获取状态栏资源ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = (int) context.getResources().getDimension(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 初始化activity的状态栏颜色
     *
     * @param activity 被着色的activity
     * @param color    状态栏颜色
     */
    public static void initStatusBar(Activity activity, @DrawableRes int color) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // window.setStatusBarColor(color);


        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup contentView = window.findViewById(android.R.id.content);
            contentView.getChildAt(0).setFitsSystemWindows(true);
            View view = new View(activity.getApplicationContext());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            view.setBackgroundResource(color);
            contentView.addView(view, layoutParams);
        }
    }
}
