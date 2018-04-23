package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.entity.AcRole;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.service.IAcRoleService;

/**
 * acRole的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acRole")
public class AcRoleController extends BaseController<AcRole>  {

    @Autowired
    private IAcRoleService acRoleService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcRole acRole) {
        acRoleService.insert(acRole);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcRole acRole) {
        acRoleService.updateById(acRole);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acRoleService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcRole acRole = acRoleService.selectById(id);
        if (acRoleService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acRole);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcRole> page) {
        return  ResultVO.success("查询成功", acRoleService.selectPage(getPage(page), getCondition(page)));
    }
    
}

