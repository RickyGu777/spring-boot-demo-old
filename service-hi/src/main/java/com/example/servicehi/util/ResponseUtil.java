package com.example.servicehi.util;

import lombok.Data;

@Data
public class ResponseUtil {
    public static final String SUCCESS = "success";

    private static final String ERROR = "error";

    public ResponseUtil() {
        this.code = "0";
        this.msg = SUCCESS;
    }

    private String msg;
    private String code;
    private String data;
}