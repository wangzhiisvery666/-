package ccut.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ccut.common.CommonResponse;
import ccut.common.ErrorEnum;


@RestControllerAdvice
@Slf4j
public class globalExceptionProcessor {

    @ExceptionHandler({RuntimeException.class})
    public <T> CommonResponse<T> runtimeException(RuntimeException exception) {
        log.info("[运行异常],[{}]", exception.getMessage());
        return new CommonResponse(exception.getMessage(), "444", null);
    }

    @ExceptionHandler({CustomizeException.class})
    public <T> CommonResponse<T> customizeException(CustomizeException exception) {
        ErrorEnum error = exception.getError();
        log.info("[发生自定义错误] : [状态码:{}  异常信息:{}  描述:{} ]", new Object[]{error.getStatusCode(), error.getMessage(), error.getDescribe()});
        return new CommonResponse(error.getMessage(), error.getStatusCode(), null);
    }
}
