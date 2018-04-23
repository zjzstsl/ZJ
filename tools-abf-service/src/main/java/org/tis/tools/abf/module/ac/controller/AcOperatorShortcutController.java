package org.tis.tools.abf.module.ac.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.ac.entity.AcOperatorShortcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.service.IAcOperatorShortcutService;

/**
 * acOperatorShortcut的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorShortcut")
public class AcOperatorShortcutController extends BaseController<AcOperatorShortcut>  {

    @Autowired
    private IAcOperatorShortcutService acOperatorShortcutService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcOperatorShortcut acOperatorShortcut) {
        acOperatorShortcutService.insert(acOperatorShortcut);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorShortcut acOperatorShortcut) {
        acOperatorShortcutService.updateById(acOperatorShortcut);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acOperatorShortcutService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorShortcut acOperatorShortcut = acOperatorShortcutService.selectById(id);
        if (acOperatorShortcutService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorShortcut);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorShortcut> page) {
        return  ResultVO.success("查询成功", acOperatorShortcutService.selectPage(getPage(page), getCondition(page)));
    }
    
}

