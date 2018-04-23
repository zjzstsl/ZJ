package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.service.IAcRoleDatascopeService;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.ac.entity.AcRoleDatascope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * acRoleDatascope的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acRoleDatascope")
public class AcRoleDatascopeController extends BaseController<AcRoleDatascope>  {

    @Autowired
    private IAcRoleDatascopeService acRoleDatascopeService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcRoleDatascope acRoleDatascope) {
        acRoleDatascopeService.insert(acRoleDatascope);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcRoleDatascope acRoleDatascope) {
        acRoleDatascopeService.updateById(acRoleDatascope);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acRoleDatascopeService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcRoleDatascope acRoleDatascope = acRoleDatascopeService.selectById(id);
        if (acRoleDatascopeService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acRoleDatascope);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcRoleDatascope> page) {
        return  ResultVO.success("查询成功", acRoleDatascopeService.selectPage(getPage(page), getCondition(page)));
    }
    
}

