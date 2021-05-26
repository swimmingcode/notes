package com.cico.virmachine.interfaces.api;

import com.cico.virmachine.infrastructure.common.exception.ExceptionFactory;
import com.cico.virmachine.infrastructure.common.response.Response;
import com.cico.virmachine.infrastructure.common.response.ResponseCode;
import com.cico.virmachine.infrastructure.common.response.ResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.cico.virmachine.infrastructure.common.response.ResponseCode.PARAMETER_EXCEPTION;

/**
 * @Describe: 全局异常处理类
 * @Author: youjiancheng
 * @date 2021/4/19 10:12
 */
@Slf4j
@RestControllerAdvice
public class ExceptionApi {

    /**
     * 参数出错统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<Void> bizExceptionHandler(MethodArgumentNotValidException e){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("传递的参数错误：");
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (int i = 0; i < allErrors.size(); i++) {
            String defaultMessage = allErrors.get(i).getDefaultMessage();
            stringBuilder.append(i+1 + ":" + defaultMessage+ " ");
        }
        log.error(stringBuilder.toString());
        return ResponseFactory.productEmptyResult(PARAMETER_EXCEPTION.getCode(),stringBuilder.toString());
    }

    /**
     * 处理空指针的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Response<Void> exceptionHandler(NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return ResponseFactory.productEmptyResult(ResponseCode.NULL_POINTER_EXCEPTION);
    }

    @ExceptionHandler(value = ExceptionFactory.class)
    public Response<Void> exceptionHandler(ExceptionFactory baseException) {
        log.error("业务异常" + baseException.getMessage());
        return ResponseFactory.productEmptyResult(baseException.getCode(),baseException.getMessage());
    }


}
