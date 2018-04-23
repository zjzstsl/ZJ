package org.tis.tools.abf.module.om.controller;

import org.tis.tools.abf.module.om.entity.OmGroupApp;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.service.IOmGroupAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * omGroupApp的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omGroupApp")
public class OmGroupAppController extends BaseController<OmGroupApp>  {

    @Autowired
    private IOmGroupAppService omGroupAppService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated OmGroupApp omGroupApp) {
        omGroupAppService.insert(omGroupApp);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmGroupApp omGroupApp) {
        omGroupAppService.updateById(omGroupApp);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        omGroupAppService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmGroupApp omGroupApp = omGroupAppService.selectById(id);
        if (omGroupAppService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omGroupApp);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmGroupApp> page) {
        return  ResultVO.success("查询成功", omGroupAppService.selectPage(getPage(page), getCondition(page)));
    }
    
}

