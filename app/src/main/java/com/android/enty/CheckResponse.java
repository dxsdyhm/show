package com.android.enty;

/**
 * @Author: dxs
 * @time: 2020/5/7
 * @Email: duanxuesong12@126.com
 */
public class CheckResponse {
    private int code;
    private String msg;
    private CheckTime data;

    public CheckResponse() {
    }

    public CheckResponse(int code, String msg, CheckTime data) {
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

    public CheckTime getData() {
        return data;
    }

    public void setData(CheckTime data) {
        this.data = data;
    }
}
