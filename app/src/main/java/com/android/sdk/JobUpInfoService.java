package com.android.sdk;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.android.utils.HttpUtils;

/**
 * @Author: dxs
 * @time: 2020/5/7
 * @Email: duanxuesong12@126.com
 */
public class JobUpInfoService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        HttpUtils.getShow();
        Log.e("dxsTest","+++++++++++++++++++++++++++>");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e("dxsTest","-------------------------->");
        return false;
    }
}
