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
import org.tis.tools.core.aop.BaseControllerExceptionHandler;
import org.tis.tools.core.exception.ToolsRuntimeException;
import org.tis.tools.core.exception.WebAppException;
import org.tis.tools.core.web.vo.ResultVO;

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
public class GlobalExceptionHandler extends BaseControllerExceptionHandler {


}
