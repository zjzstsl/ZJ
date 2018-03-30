package org.tis.tools.abf.aop;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tis.tools.abf.module.common.exception.ToolsRuntimeException;
import org.tis.tools.abf.module.common.exception.WebAppException;
import org.tis.tools.abf.module.common.web.vo.ResultVO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * describe: 统一异常处理
 *
 * @author zhaoch
 * @date 2018/3/29
 **/
@RestControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {


    /**
     * 用于处理通用异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "数据验证失败:";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + ", ";
        }
        return ResultVO.error("400", errorMesssage);
    }
    /**
     * 用于处理通用异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO methodValidateException(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errorMesssage = "参数验证失败:";
        for (ConstraintViolation<?> violation : violations) {
            errorMesssage += violation.getMessage();
        }
        return ResultVO.error("400", errorMesssage);
    }
    /**
     * 用于处理通用异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResultVO messageConversionException(HttpMessageConversionException e) {
        return ResultVO.error("400", "请求数据不合法！");
    }

    /**
     * 服务异常处理
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ToolsRuntimeException.class)
    public ResultVO handleServiceRequestError(ToolsRuntimeException ex) {
        return ResultVO.failure(ex.getCode(), ex.getMessage());
    }


    /**
     * WebApp层异常
     */
    @ExceptionHandler(WebAppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleWebAppException(WebAppException ex) {
        return ResultVO.error(ex.getCode(), ex.getMessage());
    }


    /**
     * 未知异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleUnexpectedServerError(Exception ex) {
        return ResultVO.error(ex.getMessage());
    }
}
