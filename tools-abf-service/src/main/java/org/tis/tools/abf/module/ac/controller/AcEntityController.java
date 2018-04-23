package org.tis.tools.abf.module.ac.controller;

import org.tis.tools.abf.module.ac.entity.AcEntity;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.ac.service.IAcEntityService;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * acEntity的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acEntity")
public class AcEntityController extends BaseController<AcEntity>  {

    @Autowired
    private IAcEntityService acEntityService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcEntity acEntity) {
        acEntityService.insert(acEntity);
        return ResultVO.success("新增成功！");
    }
    
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcEntity acEntity) {
        acEntityService.updateById(acEntity);
        return ResultVO.success("修改成功！");
    }
    
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        acEntityService.deleteById(id);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcEntity acEntity = acEntityService.selectById(id);
        if (acEntityService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acEntity);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcEntity> page) {
        return  ResultVO.success("查询成功", acEntityService.selectPage(getPage(page), getCondition(page)));
    }
    
}

