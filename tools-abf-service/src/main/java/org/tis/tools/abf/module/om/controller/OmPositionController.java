package org.tis.tools.abf.module.om.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.om.service.IOmPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * omPosition的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omPosition")
public class OmPositionController extends BaseController<OmPosition>  {

    @Autowired
    private IOmPositionService omPositionService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated OmPosition omPosition) {
        omPositionService.insert(omPosition);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmPosition omPosition) {
        omPositionService.updateById(omPosition);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        omPositionService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmPosition omPosition = omPositionService.selectById(id);
        if (omPositionService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omPosition);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmPosition> page) {
        return  ResultVO.success("查询成功", omPositionService.selectPage(getPage(page), getCondition(page)));
    }
    
}

