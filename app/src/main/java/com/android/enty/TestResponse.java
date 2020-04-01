package com.android.enty;

/**
 * @Author: dxs
 * @time: 2019/11/11
 * @Email: duanxuesong12@126.com
 */
public class TestResponse {
    public int code;
    public String msg;
    public work data;

    public TestResponse() {
    }

    public TestResponse(int code, String msg, work data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public work getData() {
        return data;
    }

    public void setData(work data) {
        this.data = data;
    }
}
