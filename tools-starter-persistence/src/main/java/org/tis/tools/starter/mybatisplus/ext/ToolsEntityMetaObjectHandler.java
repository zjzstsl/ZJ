package org.tis.tools.starter.mybatisplus.ext;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 填充Tools实体固定字段
 * 固定字段包括：创建时间、创建人、上次更新时间、更新人
 * </pre>
 *
 * @author Shiyunlai
 * @since 2018-03-11
 */
public class ToolsEntityMetaObjectHandler extends MetaObjectHandler {

    protected final static Logger logger = LoggerFactory.getLogger(ToolsEntityMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("新增的时候干点不可描述的事情");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("更新的时候干点不可描述的事情");
    }
}
