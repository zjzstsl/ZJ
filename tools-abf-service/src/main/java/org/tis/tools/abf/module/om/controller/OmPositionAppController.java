package org.tis.tools.abf.module.om.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.service.IOmPositionAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.om.entity.OmPositionApp;

/**
 * omPositionApp的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omPositionApp")
public class OmPositionAppController extends BaseController<OmPositionApp>  {

    @Autowired
    private IOmPositionAppService omPositionAppService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated OmPositionApp omPositionApp) {
        omPositionAppService.insert(omPositionApp);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmPositionApp omPositionApp) {
        omPositionAppService.updateById(omPositionApp);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        omPositionAppService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmPositionApp omPositionApp = omPositionAppService.selectById(id);
        if (omPositionAppService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omPositionApp);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmPositionApp> page) {
        return  ResultVO.success("查询成功", omPositionAppService.selectPage(getPage(page), getCondition(page)));
    }
    
}

