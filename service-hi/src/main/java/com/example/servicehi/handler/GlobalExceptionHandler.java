package com.example.servicehi.handler;

import com.example.servicehi.common.CodeMsg;
import com.example.servicehi.util.ResponseUtil;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseUtil<String> exceptionHandler(HttpServletRequest request, Exception e) {
        //全局异常处理逻辑
        if (e instanceof GlobalException) {
            return ResponseUtil.buildERROR(((GlobalException) e).getCodeMsg());
        }
        //绑定异常处理逻辑
        else if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> errors = exception.getAllErrors();
            String msg = errors.get(0).getDefaultMessage();
            return ResponseUtil.buildERROR(CodeMsg.SERVER_BIND_ERROR.fillArgs(msg));

        }
        return ResponseUtil.buildERROR(CodeMsg.SERVER_ERROR);
    }
}
