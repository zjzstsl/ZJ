package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.entity.AcRoleEntityfield;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.ac.service.IAcRoleEntityfieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * acRoleEntityfield的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acRoleEntityfield")
public class AcRoleEntityfieldController extends BaseController<AcRoleEntityfield>  {

    @Autowired
    private IAcRoleEntityfieldService acRoleEntityfieldService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcRoleEntityfield acRoleEntityfield) {
        acRoleEntityfieldService.insert(acRoleEntityfield);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcRoleEntityfield acRoleEntityfield) {
        acRoleEntityfieldService.updateById(acRoleEntityfield);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acRoleEntityfieldService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcRoleEntityfield acRoleEntityfield = acRoleEntityfieldService.selectById(id);
        if (acRoleEntityfieldService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acRoleEntityfield);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcRoleEntityfield> page) {
        return  ResultVO.success("查询成功", acRoleEntityfieldService.selectPage(getPage(page), getCondition(page)));
    }
    
}

