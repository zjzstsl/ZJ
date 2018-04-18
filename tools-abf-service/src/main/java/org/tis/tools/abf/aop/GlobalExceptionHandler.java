package org.tis.tools.abf.aop;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tis.tools.core.aop.BaseControllerExceptionHandler;

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
