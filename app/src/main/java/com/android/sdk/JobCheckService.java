package com.android.sdk;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.android.utils.HttpUtils;
import com.blankj.utilcode.util.LogUtils;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class JobCheckService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        HttpUtils.getShow();
        Log.e("dxsTest","+++++++++++++++++++++++++++>");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e("dxsTest","-------------------------->");
        return false;
    }
}
