package com.android.show;

import com.android.utils.HttpUtils;
import com.android.utils.SceduHelper;
import com.blankj.utilcode.util.ThreadUtils;

/**
 * @Author: dxs
 * @time: 2020/5/7
 * @Email: duanxuesong12@126.com
 */
public class CheckTask extends ThreadUtils.Task {
    @Override
    public Object doInBackground() throws Throwable {
        HttpUtils.checkDevice();
        return null;
    }

    @Override
    public void onSuccess(Object result) {
        SceduHelper.runCheckJob(ShowApplication.getContext(),2);
    }

    @Override
    public void onCancel() {
        SceduHelper.runCheckJob(ShowApplication.getContext(),2);
    }

    @Override
    public void onFail(Throwable t) {
        SceduHelper.runCheckJob(ShowApplication.getContext(),2);
    }
}
