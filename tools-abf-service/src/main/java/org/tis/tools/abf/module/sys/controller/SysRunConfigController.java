package org.tis.tools.abf.module.sys.controller;

import org.tis.tools.abf.module.sys.service.ISysRunConfigService;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.sys.entity.SysRunConfig;

/**
 * sysRunConfig的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/sysRunConfig")
public class SysRunConfigController extends BaseController<SysRunConfig>  {

    @Autowired
    private ISysRunConfigService sysRunConfigService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated SysRunConfig sysRunConfig) {
        sysRunConfigService.insert(sysRunConfig);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated SysRunConfig sysRunConfig) {
        sysRunConfigService.updateById(sysRunConfig);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        sysRunConfigService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        SysRunConfig sysRunConfig = sysRunConfigService.selectById(id);
        if (sysRunConfigService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", sysRunConfig);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<SysRunConfig> page) {
        return  ResultVO.success("查询成功", sysRunConfigService.selectPage(getPage(page), getCondition(page)));
    }
    
}

