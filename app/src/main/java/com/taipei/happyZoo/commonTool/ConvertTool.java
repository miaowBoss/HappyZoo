package com.taipei.happyZoo.commonTool;

import android.content.Context;
import android.util.DisplayMetrics;

public class ConvertTool {
    public static int dp2px(Context context, float dpValue) {
        return Math.round(dpValue * getDisplayMetrics(context).density);
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }
}
