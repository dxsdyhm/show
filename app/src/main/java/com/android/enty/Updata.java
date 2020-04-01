package com.android.enty;

import android.util.Log;

import com.blankj.utilcode.util.EncryptUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @Author: dxs
 * @time: 2019/11/12
 * @Email: duanxuesong12@126.com
 */
public class Updata {
    private String data;

    public Updata(String data) {
        this.data = getRealData(data);
    }

    public Updata() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = getRealData(data);
    }

    public String getRealData(String data) {
        try {
            byte[] k = {66, 52, 69, 70, 67, 53, 68, 51, 50, 49, 56, 70, 65, 66, 55, 50};
            byte[] result = EncryptUtils.encryptAES2Base64(data.trim().getBytes("utf-8"), k, "AES/ECB/ZeroBytePadding", null);
            return new String(result,"utf-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
