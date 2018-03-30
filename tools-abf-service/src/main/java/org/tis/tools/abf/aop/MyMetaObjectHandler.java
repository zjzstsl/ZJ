package org.tis.tools.abf.aop;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**  自定义填充公共 name 字段  */
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * 测试 user 表 name 字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 更多查看源码测试用例

        // 测试下划线
        Object testType = getFieldValByName("dataStatus", metaObject);
        System.out.println("testType=" + testType);
        if (testType == null) {
            setFieldValByName("dataStatus", "0", metaObject);
        }
    }


    @Override
    public void updateFill(MetaObject metaObject) {

    }

}
