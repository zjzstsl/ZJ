package org.tis.tools.asf.module.er.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.validation.AddValidateGroup;
import org.tis.tools.core.validation.UpdateValidateGroup;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.asf.module.er.entity.ERCategory;
import org.tis.tools.asf.module.er.entity.ERColumn;
import org.tis.tools.asf.module.er.service.IERColumnService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@RestController
@RequestMapping("erColumn")
public class ERColumnController extends BaseController<ERColumn> {

    @Autowired
    private IERColumnService columnService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated({AddValidateGroup.class}) ERColumn column) {
        columnService.insert(column);
        return ResultVO.success("新增成功！");
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated({UpdateValidateGroup.class}) ERColumn column) {
        columnService.updateById(column);
        return ResultVO.success("修改成功！");
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        columnService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        ERColumn column = columnService.selectById(id);
        if (column == null) {
            return ResultVO.error("00001", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", column);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<ERColumn> page){
        return  ResultVO.success("查询成功", columnService.selectPage(getPage(page), getCondition(page)));
    }

}
