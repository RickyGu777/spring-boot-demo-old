package com.example.servicehi.util;

import com.example.servicehi.common.CodeMsg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUtil<T> {
    public static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private String msg;
    private int code;
    private T data;

    /**
     * 仅提示成功
     */
    public ResponseUtil() {
        this.msg = SUCCESS;
    }

    /**
     * 返回对象
     *
     * @param data
     */
    public ResponseUtil(T data) {
        this.data = data;
    }

    /**
     * 自定义所有返回参数
     *
     * @param msg
     * @param code
     * @param data
     */
    public ResponseUtil(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    /**
     * 自定义返回错误信息
     *
     * @param msg
     * @return
     */
    public static ResponseUtil buildERROR(String msg) {
        return new ResponseUtil(msg, 1);
    }

    /**
     * 返回设定好的错误信息
     *
     * @param codeMsg
     * @return
     */
    public static ResponseUtil buildERROR(CodeMsg codeMsg) {
        return new ResponseUtil(codeMsg.getMsg(), 1);
    }

    /**
     * 私有方法，仅供发生错误时使用
     * @param msg
     * @param code
     */
    private ResponseUtil(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}