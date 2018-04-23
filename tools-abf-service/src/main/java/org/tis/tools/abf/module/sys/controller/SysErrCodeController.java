package org.tis.tools.abf.module.sys.controller;

import org.tis.tools.abf.module.sys.entity.SysErrCode;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.sys.service.ISysErrCodeService;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * sysErrCode的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/sysErrCode")
public class SysErrCodeController extends BaseController<SysErrCode>  {

    @Autowired
    private ISysErrCodeService sysErrCodeService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated SysErrCode sysErrCode) {
        sysErrCodeService.insert(sysErrCode);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated SysErrCode sysErrCode) {
        sysErrCodeService.updateById(sysErrCode);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        sysErrCodeService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        SysErrCode sysErrCode = sysErrCodeService.selectById(id);
        if (sysErrCodeService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", sysErrCode);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<SysErrCode> page) {
        return  ResultVO.success("查询成功", sysErrCodeService.selectPage(getPage(page), getCondition(page)));
    }
    
}

