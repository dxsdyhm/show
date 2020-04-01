package com.android.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Author: dxs
 * @time: 2019/11/12
 * @Email: duanxuesong12@126.com
 */
public class GsonUt {
    public static Gson gson=new GsonBuilder()
            .disableHtmlEscaping()
            .create();
    public static String toJson(Object object){
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json,Class<T> tClass){
        return gson.fromJson(json,tClass);
    }
}
