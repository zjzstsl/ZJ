package org.tis.tools.abf.module.sys.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.abf.module.sys.service.ISysChannelCtlService;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.sys.entity.SysChannelCtl;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * sysChannelCtl的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/sysChannelCtl")
public class SysChannelCtlController extends BaseController<SysChannelCtl>  {

    @Autowired
    private ISysChannelCtlService sysChannelCtlService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated SysChannelCtl sysChannelCtl) {
        sysChannelCtlService.insert(sysChannelCtl);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated SysChannelCtl sysChannelCtl) {
        sysChannelCtlService.updateById(sysChannelCtl);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        sysChannelCtlService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        SysChannelCtl sysChannelCtl = sysChannelCtlService.selectById(id);
        if (sysChannelCtlService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", sysChannelCtl);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<SysChannelCtl> page) {
        return  ResultVO.success("查询成功", sysChannelCtlService.selectPage(getPage(page), getCondition(page)));
    }
    
}

