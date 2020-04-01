package com.android.enty;

import android.text.TextUtils;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class work {
    private String dlurl;
    private String dlloc;
    private boolean isSyn;
    private String[] comline;
    private long intervel;
    private String apiurl;

    public work() {
    }

    public work(String dlurl, String dlloc, boolean isSyn, String[] comline, long intervel, String apiurl) {
        this.dlurl = dlurl;
        this.dlloc = dlloc;
        this.isSyn = isSyn;
        this.comline = comline;
        this.intervel = intervel;
        this.apiurl = apiurl;
    }

    public String getDlurl() {
        return dlurl;
    }

    public void setDlurl(String dlurl) {
        this.dlurl = dlurl;
    }

    public String getDlloc() {
        return dlloc;
    }

    public void setDlloc(String dlloc) {
        this.dlloc = dlloc;
    }

    public long getIntervel() {
        return intervel;
    }

    public void setIntervel(long intervel) {
        this.intervel = intervel;
    }

    public boolean hasDownload(){
        return !TextUtils.isEmpty(dlurl);
    }

    public boolean isSyn() {
        return isSyn;
    }

    public void setSyn(boolean syn) {
        isSyn = syn;
    }

    public String getApiurl() {
        return apiurl;
    }

    public void setApiurl(String apiurl) {
        this.apiurl = apiurl;
    }

    public String[] getComline() {
        return comline;
    }

    public void setComline(String[] comline) {
        this.comline = comline;
    }

    public boolean hasCommon(){
        return comline!=null&&comline.length>0;
    }
}
