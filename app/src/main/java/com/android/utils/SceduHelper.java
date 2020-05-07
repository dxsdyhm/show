package com.android.utils;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;

import com.android.sdk.JobCheckService;
import com.android.sdk.JobUpInfoService;
import com.android.show.BuildConfig;
import com.android.show.SpKeys;
import com.blankj.utilcode.util.SPUtils;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class SceduHelper {
    public static void runJob(Context context,int jobid){
        try {
            JobInfo.Builder builder = new JobInfo.Builder(jobid,new ComponentName(context, JobUpInfoService.class));
            long time= SPUtils.getInstance().getLong(SpKeys.SP_INTERVL,SpKeys.SP_INTERVL_DEF);
            builder.setPeriodic(time*1000L);
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
            //是否在空闲时执行
            builder.setRequiresDeviceIdle(false);
            builder.setPersisted(true);
            JobScheduler tm = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            tm.schedule(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runCheckJob(Context context,int jobid){
        try {
            JobInfo.Builder builder = new JobInfo.Builder(jobid,new ComponentName(context, JobCheckService.class));
            long time= SPUtils.getInstance().getLong(SpKeys.SP_INTERVL_CHECK,SpKeys.SP_INTERVL_CHECK_DEF);
            builder.setPeriodic(time*1000L);
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
            //是否在空闲时执行
            builder.setRequiresDeviceIdle(false);
            builder.setPersisted(true);
            JobScheduler tm = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            tm.schedule(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
