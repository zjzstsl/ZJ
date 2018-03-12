package org.tis.tools.starter.multidatasource.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.tis.tools.starter.multidatasource.DataSourceContextHolder;
import org.tis.tools.starter.multidatasource.annotion.DataSource;
import org.tis.tools.starter.mybatisplus.config.DruidProperties;

import java.lang.reflect.Method;

/**
 * 动态数据源AOP实现
 * 以 Before 和 After进行切入点
 * @author Shiyunlai
 * @since  2018-03-12
 */
@Deprecated
@Aspect
@Component
@ConditionalOnProperty(prefix = "tools", name = "multi-datasource-open", havingValue = "true")
public class DynamicDatasourceAspect {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DruidProperties druidProperties;

    @Pointcut(value = "@annotation(org.tis.tools.starter.multidatasource.annotion.DataSource)")
    private void cut() {

    }

    /**
     * 方法执行前拦截
     * @Before("@annotation(ds)") 的意思是：
     * @Before：在方法执行之前进行执行：
     * @annotation(targetDataSource)： 会拦截注解targetDataSource的方法，否则不拦截;
     */
    @Before("cut()")
    public void changeDataSource(JoinPoint point) throws Throwable {

        DataSource dataSource;
        dataSource = getDataSourceAnnotation(point);

        //获取当前的指定的数据源;
        String dsId = dataSource.name();

        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DataSourceContextHolder.contains(dsId)) {

            log.error("数据源[{}]不存在，使用默认数据源 > {}" + dataSource.name() + point.getSignature());

        } else {

            log.debug("Use DataSource : {} > {}" + dataSource.name() + point.getSignature());
            //找到的话，那么设置到动态数据源上下文中。
            DataSourceContextHolder.setDataSourceType(dataSource.name());

        }

    }

    /**
     * 方法执行结束后拦截
     * @param point
     * @throws Throwable
     */
    @After("cut()")
    public void restoreDataSource(JoinPoint point) throws Throwable {

        DataSource dataSource;
        dataSource = getDataSourceAnnotation(point);
        log.debug("Revert DataSource : {} > {}" + dataSource.name() + point.getSignature());

        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DataSourceContextHolder.clearDataSourceType();
    }

    /**
     * 从point上获取注解对象
      * @param point
     * @return
     * @throws Exception
     */
    private DataSource getDataSourceAnnotation(JoinPoint point) throws Exception{

        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();

        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        DataSource datasource = currentMethod.getAnnotation(DataSource.class);
        return datasource;

    }

}
