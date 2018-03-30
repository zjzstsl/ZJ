package org.tis.tools.abf.module.common.web.vo;

import javax.validation.constraints.NotNull;

public class SmartPage<T> {

    @NotNull(message = "分页参数不能为空")
    private PageVO page;

    private T condition;

    public PageVO getPage() {
        return page;
    }

    public void setPage(PageVO page) {
        this.page = page;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}
