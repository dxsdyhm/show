package com.android.show;

import com.android.utils.SystemInfo;
import com.blankj.utilcode.util.ThreadUtils;

/**
 * @Author: dxs
 * @time: 2020/5/7
 * @Email: duanxuesong12@126.com
 */
public class ShutdownTask extends ThreadUtils.Task {
    @Override
    public Object doInBackground() throws Throwable {
        SystemInfo.shutDowm();
        return null;
    }

    @Override
    public void onSuccess(Object result) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFail(Throwable t) {

    }
}
