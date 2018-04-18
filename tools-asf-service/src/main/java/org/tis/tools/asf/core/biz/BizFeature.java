package org.tis.tools.asf.core.biz;

import lombok.Data;

/**
 * 特性/功能 如校验功能
 */
@Data
public class BizFeature {

    private String id;

    /**
     * 每个特性会对应一个文件
     */
    private String fileId;

    private String name;

    /**
     * 每个特性可能会有额外的文件，如校验分组需要额外的接口类
     */
    private String extFiles;

    private String config;

    private Boolean necessary = false;

}
