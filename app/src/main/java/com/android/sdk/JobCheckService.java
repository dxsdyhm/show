package com.android.sdk;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.android.utils.HttpUtils;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class JobCheckService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        HttpUtils.checkDevice();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
