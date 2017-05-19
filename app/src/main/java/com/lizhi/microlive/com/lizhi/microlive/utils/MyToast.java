package com.lizhi.microlive.com.lizhi.microlive.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/13.
 *
 */

public class MyToast {

    public static final void showToast(Context context, String info, int time){
        Toast.makeText(context.getApplicationContext(), info, time).show();

    }
}
