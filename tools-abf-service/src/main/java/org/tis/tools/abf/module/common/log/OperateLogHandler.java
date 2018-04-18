package org.tis.tools.abf.module.common.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tis.tools.abf.module.common.log.enums.OperateResult;
import org.tis.tools.abf.module.common.log.vo.LogOperateDetail;
import org.tis.tools.abf.module.jnl.service.ILogAbfOperateService;
import org.tis.tools.core.exception.ToolsRuntimeException;
import org.tis.tools.core.utils.BasicUtil;
import org.tis.tools.core.web.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class OperateLogHandler {

    @Autowired
    private ILogAbfOperateService logOperatorRService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 异步操作记录日志的线程池
     */
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10,
            new ThreadFactoryBuilder().setNameFormat("abf-log-pool-%d").build());

    @Pointcut("@annotation(org.tis.tools.abf.module.common.log.OperateLog)")
    public void methodCachePointcut() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void requestPointcut() {}

    /**
     * 统一处理 LOG4J
     * 进入RequestMapping注解的controller方法前
     */
    @Before("requestPointcut()")
    public void enterController(JoinPoint point) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String reqInfo = "{}";
        String uri = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 0) {
            uri = uri + pathInfo;
        }
        String paramString = getMethodInfo(point);
        logger.info(" [请求] Request URL:{}; Request Method:{}; Request Body:{}", BasicUtil.wrap(uri,
                request.getMethod(), paramString)) ;
    }

    @AfterReturning(value = "requestPointcut()", returning = "ret")
    public void exitController(JoinPoint point, ResultVO ret) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String objStr = JSON.toJSONString(ret.getResult());
        String uri = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 0) {
            uri = uri + pathInfo;
        }
        logger.info(" [响应] Request URL:{}; Response Body:{}", BasicUtil.wrap(uri, objStr)) ;
    }

    /**
     * 进入OperateLog注解的controller方法前
     * @param point
     * @throws Throwable
     */
    @Before("@annotation(log)")
    public void enterOperateController(JoinPoint point, OperateLog log) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        Session session = SecurityUtils.getSubject().getSession();
//        AcOperator acOperator = (AcOperator) session.getAttribute("userInfo");
        OperateLogBuilder logBuilder = new OperateLogBuilder();
        logBuilder.start()
                // FIXME 从哪设置该值
                .setOperateFrom("ABF")
                .setUserId("开发用户TODO")
                .setOperatorName("开发用户TODO")
//                .setUserId(StringUtils.equals(jnl.operateType(),"login") ? "" : session.getAttribute("userId").toString())
//                .setOperatorName(StringUtils.equals(jnl.operateType(), "login") ? "" : acOperator.getOperatorName())
                .setOperateType(log.operateType())
                .setProcessDesc(log.operateDesc())
                .setRestfulUrl(request.getPathInfo());
        LogThreadLocal.setLogBuilderLocal(logBuilder);
    }

    /**
     * controller执行没有异常完毕后
     *
     * @param point
     */
    @AfterReturning(value = "@annotation(logAnt)", returning = "ret")
    public void logAfterExecution(JoinPoint point, ResultVO ret, OperateLog logAnt) throws Throwable {
        LogOperateDetail log = LogThreadLocal.getLogBuilderLocal().getLog();
        log.setOperateResult(OperateResult.SUCCESS);

        // 添加数据变化项（LogAbfChange）到操作日志
        JSONObject reqData = new JSONObject();

        for(Object arg : point.getArgs()){
            if (arg != null &&  String.class.equals(arg.getClass())) {
                try{
                    reqData = JSONObject.parseObject(String.valueOf(arg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String objStr = JSON.toJSONString(ret.getResult());
        if(logAnt.retType() == ReturnType.Object) {
            JSONObject jsonObject = JSONObject.parseObject(objStr);
            JSONObject changeData = reqData.getJSONObject("changeData");
            if(StringUtils.isNotBlank(logAnt.id())) {
                log.addObj()
                        .setObjGuid(jsonObject.getString(logAnt.id()))
                        .setObjName(StringUtils.isBlank(logAnt.name()) ? null : jsonObject.getString(logAnt.name()))
                        .setObjValue(objStr);
                for (String key : logAnt.keys()) {
                    log.getObj(0).addKey(key, jsonObject.getString(key));
                }
                if (changeData != null) {
                    changeData.keySet().forEach(key -> log.getObj(0).addChangeItem(key, changeData.getString(key)));
                }
            }

        } else if(logAnt.retType() == ReturnType.List) {
            JSONArray array = JSONObject.parseArray(objStr);
            JSONArray changeData = reqData.getJSONArray("changeData");
            if(StringUtils.isNotBlank(logAnt.id())) {
                for (int i = 0; i < array.size(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    log.addObj().setObjGuid(jsonObject.getString(logAnt.id())).setObjValue(jsonObject.toJSONString());
                    for (String key : logAnt.keys()) {
                        log.getObj(i).addKey(key, jsonObject.getString(key));
                    }
                    int finalI = i;
                    if (changeData != null) {
                        changeData.getJSONObject(i).keySet()
                                .forEach(key ->
                                        log.getObj(finalI).addChangeItem(key, changeData.getJSONObject(finalI).getString(key)));
                    }
                }
            }
        }
        saveLogInfo();
    }

    /**
     * controller执行抛出异常完毕后
     *
     * @param point
     */
    @AfterThrowing(value = "methodCachePointcut()", throwing = "e")
    public void logAfterExecutionThrowException(JoinPoint point, Exception e) throws Throwable {
        OperateLogBuilder logBuilder = LogThreadLocal.getLogBuilderLocal();

        if(e instanceof ToolsRuntimeException) {
            logBuilder.getLog().setOperateResult(OperateResult.FAILURE);
        } else {
            logBuilder.getLog().setOperateResult(OperateResult.ERROR);
        }

        // 获取堆栈String
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        logBuilder.getLog().setStackTrace(sw.toString().length() > 4000 ?
                sw.toString().substring(0, 3999) : sw.toString());
        saveLogInfo();
        pw.close();
        sw.close();

    }

    private void saveLogInfo() {

        try {
            // 日志记录操作延时
            int operateDelayTime = 10;
            executor.schedule(businessLog(LogThreadLocal.getLogBuilderLocal().getLog())
                    , operateDelayTime, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("保存日志出错");
        }
    }

    private TimerTask businessLog(LogOperateDetail log) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    logOperatorRService.createOperatorLog(log);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    private String getMethodInfo(JoinPoint point) {
        String className = point.getSignature().getDeclaringType().getSimpleName();
        String methodName = point.getSignature().getName();
        String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
        StringBuilder sb = null;
        if (Objects.nonNull(parameterNames)) {
            sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                String value = point.getArgs()[i] != null ? point.getArgs()[i].toString() : "null";
                sb.append(parameterNames[i]).append(":").append(value).append("; ");
            }
        }
        sb = sb == null ? new StringBuilder() : sb;
        String info = String.format("{class:%s | method:%s | args:[%s]}", className, methodName, sb.toString());
        return info;
    }


}
