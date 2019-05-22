package com.example.servicehi.handler;

import com.example.servicehi.common.CodeMsg;
import com.example.servicehi.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseUtil<String> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("异常捕获,异常类型:[{}],异常发生时间:[{}],异常信息:[{}]", e.getClass().getName(), new Date(), ((GlobalException) e).getCodeMsg());
        log.error("异常打印");
        e.printStackTrace();
        //全局异常处理逻辑
        if (e instanceof GlobalException) {
            return ResponseUtil.buildERROR(((GlobalException) e).getCodeMsg());
        }
        // Validate 数据校验错误
        if (e instanceof IllegalArgumentException) {
            return ResponseUtil.buildERROR("数据校验错误:" + e.getMessage());
        }
        //绑定异常处理逻辑
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> errors = exception.getAllErrors();
            String msg = errors.get(0).getDefaultMessage();
            return ResponseUtil.buildERROR(CodeMsg.SERVER_BIND_ERROR.fillArgs(msg));
        }
        if (e instanceof NullPointerException) {
            return ResponseUtil.buildERROR("空指针异常，请检查数据");
        }
        return ResponseUtil.buildERROR(CodeMsg.SERVER_ERROR);
    }
}
