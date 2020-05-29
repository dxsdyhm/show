package com.android.show;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class Config {
    public enum DeviceType {
        KUANGJI,
        JIDINGHE
    }

    public static final DeviceType DEVICE_TYPE = DeviceType.JIDINGHE;

    public static String Key1="info";
    public static String Key2="pkg";
    public static int SP_CHECK_GAP_DEFAULT=180;
}
