package com.example.servicehi.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUtil {
    public static final String SUCCESS = "success";

    private static final String ERROR = "error";

    public ResponseUtil() {
        this.code = "0";
        this.msg = SUCCESS;
    }

    public ResponseUtil(Object data) {
        this.data = data;
    }

    private String msg;
    private String code;
    private Object data;
}