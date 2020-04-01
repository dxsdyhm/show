package com.android.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.android.show.ShowApplication;
import com.android.utils.SceduHelper;

/**
 * @Author: dxs
 * @time: 2019/11/5
 * @Email: duanxuesong12@126.com
 */
public class BootReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            try {
                SceduHelper.runJob(ShowApplication.getContext(),1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
