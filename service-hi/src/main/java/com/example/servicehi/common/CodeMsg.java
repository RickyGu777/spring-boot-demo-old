package com.example.servicehi.common;

import lombok.ToString;

@ToString
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

    // 权限
    public static CodeMsg AUTH_CONTROLLER_BASE_ERROR = new CodeMsg(20001, "Auth服务端异常");

    // 图片上传
    public static CodeMsg IMAGE_CONTROLLER_BASE_ERROR = new CodeMsg(30001, "图片服务异常");
    public static CodeMsg IMAGE_CONTROLLER_UPLOAD_IMAGE_ERROR = new CodeMsg(30002, "保存图片数据失败");
    public static CodeMsg IMAGE_CONTROLLER_QRCODE_ERROR = new CodeMsg(30003, "二维码解析错误");

    // 优惠券
    public static CodeMsg SHARE_TICKET_CONTROLLER_REPEAT_ERROR = new CodeMsg(40001, "优惠券码已存在,请勿重复添加");

    // 工具类异常
    public static CodeMsg SAVE_IMG_POST_REQUEST_ERROR = new CodeMsg(90001, "上传图床工具类，POST请求失败");

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