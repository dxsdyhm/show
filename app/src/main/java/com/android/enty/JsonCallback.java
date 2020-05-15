package com.android.enty;

import com.blankj.utilcode.util.GsonUtils;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        T data=null;
        try {
            ResponseBody body=response.body();
            ShowResponse showResponse=GsonUtils.fromJson(body.string(),ShowResponse.class);
            if(showResponse.getCode()==0){
                return null;
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onSuccess(Response<T> response) {

    }
}
