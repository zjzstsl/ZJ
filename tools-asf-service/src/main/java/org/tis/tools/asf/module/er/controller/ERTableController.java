package org.tis.tools.asf.module.er.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.validation.AddValidateGroup;
import org.tis.tools.core.validation.UpdateValidateGroup;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.asf.module.er.entity.ERColumn;
import org.tis.tools.asf.module.er.entity.ERTable;
import org.tis.tools.asf.module.er.service.IERTableService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@RestController
@RequestMapping("/erTable")
public class ERTableController extends BaseController<ERTable> {

    @Autowired
    private IERTableService tableService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated({AddValidateGroup.class}) ERTable table) {
        tableService.insert(table);
        return ResultVO.success("新增成功！");
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated({UpdateValidateGroup.class}) ERTable table) {
        tableService.updateById(table);
        return ResultVO.success("修改成功！");
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        tableService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        ERTable table = tableService.selectById(id);
        if (table == null) {
            return ResultVO.error("00001", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", table);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<ERTable> page){
        return  ResultVO.success("查询成功", tableService.selectPage(getPage(page), getCondition(page)));
    }
}
