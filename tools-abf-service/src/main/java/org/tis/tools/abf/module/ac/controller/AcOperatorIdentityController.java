package org.tis.tools.abf.module.ac.controller;

import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.abf.module.ac.service.IAcOperatorIdentityService;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentity;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * acOperatorIdentity的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorIdentity")
public class AcOperatorIdentityController extends BaseController<AcOperatorIdentity>  {

    @Autowired
    private IAcOperatorIdentityService acOperatorIdentityService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcOperatorIdentity acOperatorIdentity) {
        acOperatorIdentityService.insert(acOperatorIdentity);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorIdentity acOperatorIdentity) {
        acOperatorIdentityService.updateById(acOperatorIdentity);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acOperatorIdentityService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorIdentity acOperatorIdentity = acOperatorIdentityService.selectById(id);
        if (acOperatorIdentityService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorIdentity);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorIdentity> page) {
        return  ResultVO.success("查询成功", acOperatorIdentityService.selectPage(getPage(page), getCondition(page)));
    }
    
}

