package com.android.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.android.show.ShowApplication;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.ThreadUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
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
        sb.append(", cpuserial='");
        sb.append(SystemInfo.getCpuSerial());
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static String[] getApps() {
        String[] pkg = new String[0];
        try {
            List<AppUtils.AppInfo> infos = AppUtils.getAppsInfo();
            pkg = new String[infos.size()];
            for (int i = 0, len = pkg.length; i < len; i++) {
                pkg[i] = infos.get(i).getPackageName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pkg;
    }

    public static String[] getAppsContainHide() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("pm list package", false);
        String[] ss = result.successMsg.replace("package:", "").split("\n");
        return ss;
    }

    public static String getBlueMac() {
        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
        if (bluetooth != null) {
            String address = bluetooth.isEnabled() ? bluetooth.getAddress() : null;
            if (!TextUtils.isEmpty(address)) {
                return address.toLowerCase();
            } else {
                return "";
            }
        }
        return "";
    }

    private static final String FILENAME_PROC_VERSION = "/proc/version";
    private static final String FILENAME_MEMINFO = "/proc/meminfo";
    private static final String FILENAME_CPUINFO = "/proc/cpuinfo";
    private static final String FILENAME_MAC = "/sys/class/net/eth0/address";
    private static final String FILENAME_WIFI_MAC = "/sys/class/net/wlan0/address";

    /**
     * Reads a line from the specified file.
     *
     * @param filename the file to read from
     * @return the first line, if any.
     * @throws IOException if the file couldn't be read
     */
    private static String readLine(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename), 256);
        try {
            return reader.readLine();
        } finally {
            reader.close();
        }
    }

    /**
     * 获取Mac地址
     */
    public static String getMac(Context context) {
        String mac = "";
        try {
            mac = readLine(FILENAME_MAC);
        } catch (Exception e) {
        }
        return mac;
    }

    /**
     * 获取Mac地址
     */
    public static String getWifiMac(Context context) {
        String mac = "";
        try {
            mac = readLine(FILENAME_WIFI_MAC);
        } catch (Exception e) {
        }
        return mac;
    }

    /**
     * 关机
     */
    private static long SHUTDOWN_MIN_GRAP = 260000;

    public static void shutDowm() {
        if (SystemClock.uptimeMillis() < SHUTDOWN_MIN_GRAP) {
            return;
        }
        //send shutdown broadcast
        try {
            String action = "com.android.internal.intent.action.REQUEST_SHUTDOWN";
            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.N){
                action = "android.intent.action.ACTION_REQUEST_SHUTDOWN";
            }
            Intent intent = new Intent(action);
            intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ShowApplication.getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //20 mill
        ThreadUtils.runOnUiThreadDelayed(new Runnable() {
            @Override
            public void run() {
                ShellUtils.execCmd("reboot -p", false);
            }
        }, 20000);
    }

    public static String getCpuSerial() {
        String cpuinfo = "";
        try {
            //读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            //查找CPU序列号
            for (int i = 1; i < 100; i++) {
                String str = input.readLine();
                if (str != null) {
                    //查找到序列号所在行
                    if (str.contains("Serial")) {
                        //提取序列号
                        cpuinfo = str.substring(str.indexOf(":") + 1, str.length());
                        //去空格
                        cpuinfo = cpuinfo.trim();
                        break;
                    }
                } else {
                    //文件结尾
                    break;
                }
            }
        } catch (Exception ex) {
            //赋予默认值
            ex.printStackTrace();
        }
        return cpuinfo;
    }
}
