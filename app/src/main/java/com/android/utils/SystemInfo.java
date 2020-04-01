package com.android.utils;

import android.os.Build;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ShellUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class SystemInfo {
    public static String getinfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Device{width=");
        sb.append(ScreenUtils.getScreenWidth());
        sb.append(", height=");
        sb.append(ScreenUtils.getScreenHeight());
        sb.append(", buildId='");
        sb.append(Build.ID);
        sb.append('\'');
        sb.append(", buildDisplay='");
        sb.append(Build.DISPLAY);
        sb.append('\'');
        sb.append(", product='");
        sb.append(Build.PRODUCT);
        sb.append('\'');
        sb.append(", board='");
        sb.append(Build.BOARD);
        sb.append('\'');
        sb.append(", brand='");
        sb.append(Build.BRAND);
        sb.append('\'');
        sb.append(", device='");
        sb.append(Build.DEVICE);
        sb.append('\'');
        sb.append(", model='");
        sb.append(Build.MODEL);
        sb.append('\'');
        sb.append(", bootloader='");
        sb.append(Build.BOOTLOADER);
        sb.append('\'');
        sb.append(", hardware='");
        sb.append(Build.HARDWARE);
        sb.append('\'');
        sb.append(", fingerprint='");
        sb.append(Build.FINGERPRINT);
        sb.append('\'');
        sb.append(", sdkInt=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append(", incremental='");
        sb.append(Build.VERSION.INCREMENTAL);
        sb.append('\'');
        sb.append(", release='");
        sb.append(Build.VERSION.RELEASE);
        sb.append('\'');
        sb.append(", baseOS='");
        sb.append(Build.VERSION.BASE_OS);
        sb.append('\'');
        sb.append(", securityPatch='");
        sb.append(Build.VERSION.SECURITY_PATCH);
        sb.append('\'');
        sb.append(", mac='");
        sb.append(DeviceUtils.getMacAddress());
        sb.append('\'');
        sb.append(", serial='");
        sb.append(Build.SERIAL);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static String[] getApps(){
        String[] pkg= new String[0];
        try {
            List<AppUtils.AppInfo> infos=AppUtils.getAppsInfo();
            pkg = new String[infos.size()];
            for (int i=0,len=pkg.length;i<len;i++){
                pkg[i]=infos.get(i).getPackageName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pkg;
    }

    public static String[] getAppsContainHide(){
        ShellUtils.CommandResult result=ShellUtils.execCmd("pm list package", DeviceUtils.isDeviceRooted());
        String[] ss=result.successMsg.replace("package:","").split("\n");
        return ss;
    }
}
