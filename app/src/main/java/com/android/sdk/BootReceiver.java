package com.android.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.show.CheckTask;
import com.android.show.ShowApplication;
import com.android.utils.HttpUtils;
import com.android.utils.SceduHelper;
import com.blankj.utilcode.util.ThreadUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author: dxs
 * @time: 2019/11/5
 * @Email: duanxuesong12@126.com
 */
public class BootReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    static final String ACTION_TEST = "android.intent.action.CHECK_TEST";
    static final String ACTION_UPDATE_TEST = "android.intent.action.UPDATE_TEST";

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            try {
                SceduHelper.runJob(ShowApplication.getContext(),1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ThreadUtils.executeBySingleWithDelay(new CheckTask(),10, TimeUnit.MINUTES);
        }else if(intent.getAction().equals(ACTION_TEST)){
            HttpUtils.checkDevice(true);
        }else if(ACTION_UPDATE_TEST.equals(intent.getAction())){
            HttpUtils.getShow();
        }
    }
}
