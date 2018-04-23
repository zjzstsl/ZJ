package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.service.IAcOperatorMenuService;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.entity.AcOperatorMenu;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * acOperatorMenu的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorMenu")
public class AcOperatorMenuController extends BaseController<AcOperatorMenu>  {

    @Autowired
    private IAcOperatorMenuService acOperatorMenuService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcOperatorMenu acOperatorMenu) {
        acOperatorMenuService.insert(acOperatorMenu);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorMenu acOperatorMenu) {
        acOperatorMenuService.updateById(acOperatorMenu);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acOperatorMenuService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorMenu acOperatorMenu = acOperatorMenuService.selectById(id);
        if (acOperatorMenuService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorMenu);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorMenu> page) {
        return  ResultVO.success("查询成功", acOperatorMenuService.selectPage(getPage(page), getCondition(page)));
    }
    
}

