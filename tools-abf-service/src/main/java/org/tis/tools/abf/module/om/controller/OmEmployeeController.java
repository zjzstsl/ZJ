package org.tis.tools.abf.module.om.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.om.service.IOmEmployeeService;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * omEmployee的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omEmployee")
public class OmEmployeeController extends BaseController<OmEmployee>  {

    @Autowired
    private IOmEmployeeService omEmployeeService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated OmEmployee omEmployee) {
        omEmployeeService.insert(omEmployee);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmEmployee omEmployee) {
        omEmployeeService.updateById(omEmployee);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        omEmployeeService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmployee omEmployee = omEmployeeService.selectById(id);
        if (omEmployeeService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omEmployee);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmEmployee> page) {
        return  ResultVO.success("查询成功", omEmployeeService.selectPage(getPage(page), getCondition(page)));
    }
    
}

