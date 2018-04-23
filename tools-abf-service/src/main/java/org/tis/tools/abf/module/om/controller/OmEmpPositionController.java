package org.tis.tools.abf.module.om.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.entity.OmEmpPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.om.service.IOmEmpPositionService;

/**
 * omEmpPosition的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omEmpPosition")
public class OmEmpPositionController extends BaseController<OmEmpPosition>  {

    @Autowired
    private IOmEmpPositionService omEmpPositionService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated OmEmpPosition omEmpPosition) {
        omEmpPositionService.insert(omEmpPosition);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmEmpPosition omEmpPosition) {
        omEmpPositionService.updateById(omEmpPosition);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        omEmpPositionService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmpPosition omEmpPosition = omEmpPositionService.selectById(id);
        if (omEmpPositionService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omEmpPosition);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmEmpPosition> page) {
        return  ResultVO.success("查询成功", omEmpPositionService.selectPage(getPage(page), getCondition(page)));
    }
    
}

