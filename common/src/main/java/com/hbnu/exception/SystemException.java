package com.hbnu.exception;


import com.hbnu.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  /**处理controller层发送的异常*/
@Slf4j  /**引入日志*/
public class SystemException {
    @ExceptionHandler(RuntimeException.class)  /**运行时生效*/
    public SysResult exception(Throwable throwable){
        throwable.printStackTrace();
        log.info(throwable.getMessage());
        return SysResult.fail("服务器调用失败！！！");
    }
}
