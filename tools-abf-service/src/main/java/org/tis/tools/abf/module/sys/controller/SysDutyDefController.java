package org.tis.tools.abf.module.sys.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.sys.service.ISysDutyDefService;
import org.tis.tools.abf.module.sys.entity.SysDutyDef;

/**
 * sysDutyDef的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/sysDutyDef")
public class SysDutyDefController extends BaseController<SysDutyDef>  {

    @Autowired
    private ISysDutyDefService sysDutyDefService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated SysDutyDef sysDutyDef) {
        sysDutyDefService.insert(sysDutyDef);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated SysDutyDef sysDutyDef) {
        sysDutyDefService.updateById(sysDutyDef);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        sysDutyDefService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        SysDutyDef sysDutyDef = sysDutyDefService.selectById(id);
        if (sysDutyDefService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", sysDutyDef);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<SysDutyDef> page) {
        return  ResultVO.success("查询成功", sysDutyDefService.selectPage(getPage(page), getCondition(page)));
    }
    
}

