package org.tis.tools.abf.module.ac.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.entity.AcApp;

/**
 * acApp的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acApp")
public class AcAppController extends BaseController<AcApp>  {

    @Autowired
    private IAcAppService acAppService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcApp acApp) {
        acAppService.insert(acApp);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcApp acApp) {
        acAppService.updateById(acApp);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acAppService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcApp acApp = acAppService.selectById(id);
        if (acAppService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acApp);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcApp> page) {
        return  ResultVO.success("查询成功", acAppService.selectPage(getPage(page), getCondition(page)));
    }
    
}

