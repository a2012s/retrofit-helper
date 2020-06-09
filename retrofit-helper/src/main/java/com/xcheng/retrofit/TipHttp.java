package com.xcheng.retrofit;

public class TipHttp {
    private int code = -1;
    private String msg;

    public TipHttp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return code == 0;
    }
}
