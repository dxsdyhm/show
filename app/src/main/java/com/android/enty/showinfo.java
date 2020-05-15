package com.android.enty;

import com.blankj.utilcode.util.DeviceUtils;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class showinfo {
    public String id="";
    public String info="";
    public String[] pkg=new String[0];

    public showinfo() {
        this.id= DeviceUtils.getAndroidID()+"_"+DeviceUtils.getMacAddress();
    }

    public showinfo(String info, String[] pkg) {
        this.id= DeviceUtils.getAndroidID()+"_"+ DeviceUtils.getMacAddress();
        this.info = info;
        this.pkg = pkg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String[] getPkg() {
        return pkg;
    }

    public void setPkg(String[] pkg) {
        this.pkg = pkg;
    }
}
