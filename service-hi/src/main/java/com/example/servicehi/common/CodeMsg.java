package com.example.servicehi.common;

public class CodeMsg {
    private int code;
    private String msg;

    // 通用异常
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    //注意  %s ，格式化字符串
    public static CodeMsg SERVER_BIND_ERROR = new CodeMsg(500101, "服务端绑定异常:%s");
    public static CodeMsg AHRI_URL_CONRTROLLER_ERROR_REPORT_URL = new CodeMsg(10001, "有重复URL");
    public static CodeMsg AHRI_URL_CONRTROLLER_ERROR_INSERT_ERROR = new CodeMsg(10002, "新增URL出错");
    public static CodeMsg AHRI_URL_CONRTROLLER_ERROR_DOWNLOAD_ERROR = new CodeMsg(10002, "下载出错");

    public static CodeMsg AUTH_CONTROLLER_BASE_ERROR = new CodeMsg(20001, "Auth服务端异常");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(msg, args);
        return new CodeMsg(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}