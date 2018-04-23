package org.tis.tools.abf.module.om.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.service.IOmEmpOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.om.entity.OmEmpOrg;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * omEmpOrg的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omEmpOrg")
public class OmEmpOrgController extends BaseController<OmEmpOrg>  {

    @Autowired
    private IOmEmpOrgService omEmpOrgService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated OmEmpOrg omEmpOrg) {
        omEmpOrgService.insert(omEmpOrg);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmEmpOrg omEmpOrg) {
        omEmpOrgService.updateById(omEmpOrg);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        omEmpOrgService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmpOrg omEmpOrg = omEmpOrgService.selectById(id);
        if (omEmpOrgService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omEmpOrg);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmEmpOrg> page) {
        return  ResultVO.success("查询成功", omEmpOrgService.selectPage(getPage(page), getCondition(page)));
    }
    
}

