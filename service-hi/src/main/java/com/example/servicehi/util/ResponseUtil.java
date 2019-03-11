package com.example.servicehi.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUtil {
    public static final String SUCCESS = "success";

    private static final String ERROR = "error";

    public ResponseUtil() {
        this.msg = SUCCESS;
    }

    public static ResponseUtil buildSUCCESS() {
        return new ResponseUtil();
    }

    public static ResponseUtil buildERROR() {
        return new ResponseUtil(ResponseUtil.ERROR, 1);
    }

    public ResponseUtil(Object data) {
        this.data = data;
    }

    public ResponseUtil(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public ResponseUtil(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    private String msg;
    private int code;
    private Object data;
}