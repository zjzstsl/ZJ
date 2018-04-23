package org.tis.tools.abf.module.ac.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.service.IAcFuncAttrService;
import org.tis.tools.abf.module.ac.entity.AcFuncAttr;

/**
 * acFuncAttr的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acFuncAttr")
public class AcFuncAttrController extends BaseController<AcFuncAttr>  {

    @Autowired
    private IAcFuncAttrService acFuncAttrService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcFuncAttr acFuncAttr) {
        acFuncAttrService.insert(acFuncAttr);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcFuncAttr acFuncAttr) {
        acFuncAttrService.updateById(acFuncAttr);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acFuncAttrService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcFuncAttr acFuncAttr = acFuncAttrService.selectById(id);
        if (acFuncAttrService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acFuncAttr);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcFuncAttr> page) {
        return  ResultVO.success("查询成功", acFuncAttrService.selectPage(getPage(page), getCondition(page)));
    }
    
}

