package org.tis.tools.core.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tis.tools.core.exception.ToolsRuntimeException;
import org.tis.tools.core.exception.WebAppException;
import org.tis.tools.core.web.vo.ResultVO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author
 * @date 2018/04/18
 */
public class BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

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
