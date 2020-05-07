package com.android.enty;

/**
 * @Author: dxs
 * @time: 2020/5/7
 * @Email: duanxuesong12@126.com
 */
public class CheckTime {
    private long checkTimespan;

    public CheckTime() {
    }

    public CheckTime(long checkTimespan) {
        this.checkTimespan = checkTimespan;
    }

    public long getCheckTimespan() {
        return checkTimespan;
    }

    public void setCheckTimespan(long checkTimespan) {
        this.checkTimespan = checkTimespan;
    }
}
