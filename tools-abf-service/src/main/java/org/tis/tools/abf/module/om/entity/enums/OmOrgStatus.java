package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * describe: 机构状态枚举类
 *
 * @author zhaoch
 * @date 2018/3/27
**/
public enum OmOrgStatus implements BaseEnum {

    /** 机构状态：正常 */
    RUNNING("running", "正常"),

    /** 机构状态：注销 */
    ANCEL("cancel", "注销"),

    /** 机构状态：停用 */
    STOP("stop", "停用");

    private final String value;

    private final String name;

    OmOrgStatus(final String value, final String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public Serializable deserialze() {
        return value;
    }
}
