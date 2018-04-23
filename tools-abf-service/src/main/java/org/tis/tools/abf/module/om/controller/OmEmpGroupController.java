package org.tis.tools.abf.module.om.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.service.IOmEmpGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.om.entity.OmEmpGroup;

/**
 * omEmpGroup的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omEmpGroup")
public class OmEmpGroupController extends BaseController<OmEmpGroup>  {

    @Autowired
    private IOmEmpGroupService omEmpGroupService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated OmEmpGroup omEmpGroup) {
        omEmpGroupService.insert(omEmpGroup);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmEmpGroup omEmpGroup) {
        omEmpGroupService.updateById(omEmpGroup);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        omEmpGroupService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmpGroup omEmpGroup = omEmpGroupService.selectById(id);
        if (omEmpGroupService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omEmpGroup);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmEmpGroup> page) {
        return  ResultVO.success("查询成功", omEmpGroupService.selectPage(getPage(page), getCondition(page)));
    }
    
}

