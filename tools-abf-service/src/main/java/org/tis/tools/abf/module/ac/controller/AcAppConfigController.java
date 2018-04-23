package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;

/**
 * acAppConfig的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acAppConfig")
public class AcAppConfigController extends BaseController<AcAppConfig>  {

    @Autowired
    private IAcAppConfigService acAppConfigService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcAppConfig acAppConfig) {
        acAppConfigService.insert(acAppConfig);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcAppConfig acAppConfig) {
        acAppConfigService.updateById(acAppConfig);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acAppConfigService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcAppConfig acAppConfig = acAppConfigService.selectById(id);
        if (acAppConfigService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acAppConfig);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcAppConfig> page) {
        return  ResultVO.success("查询成功", acAppConfigService.selectPage(getPage(page), getCondition(page)));
    }
    
}

