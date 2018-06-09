package com.example.administrator.simulation_weekone;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2018/6/9.
 */

public class AppUtils {
    public static int screenWidth(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return  metrics.widthPixels;
    }
}
