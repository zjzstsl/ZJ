package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.request.RestRequest;

@Data
public class OmOrgAddRequest extends RestRequest {

    @NotBlank(message = "区域代码不能为空")
    private String areaCode;

    @NotBlank(message = "机构等级不能为空")
    private String orgDegree;

    @NotBlank(message = "机构名称不能为空")
    private String orgName;

    @NotBlank(message = "机构类型不能为空")
    private String orgType;

    private String guidParents;
}
