package org.tis.tools.asf.module.biz.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.validation.AddValidateGroup;
import org.tis.tools.core.validation.UpdateValidateGroup;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.asf.module.biz.entity.BizField;
import org.tis.tools.asf.module.biz.service.IBizFieldService;

import java.util.List;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@RestController
@RequestMapping("bizField")
@Validated
public class BizFieldController extends BaseController<BizField> {
    @Autowired
    private IBizFieldService bizFieldService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated({AddValidateGroup.class}) BizField bizField) {
        bizFieldService.insert(bizField);
        return ResultVO.success("新增成功！");
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated({UpdateValidateGroup.class}) BizField bizField) {
        bizFieldService.updateById(bizField);
        return ResultVO.success("修改成功！");
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        bizFieldService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        BizField bizField = bizFieldService.selectById(id);
        if (bizField == null) {
            return ResultVO.error("00001", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", bizField);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<BizField> page){
        return  ResultVO.success("查询成功", bizFieldService.selectPage(getPage(page), getCondition(page)));
    }

    @PostMapping("/batchAdd")
    public ResultVO batchAdd(@RequestBody @Validated({AddValidateGroup.class}) List<BizField> bizFields) {
        bizFieldService.insertBatch(bizFields);
        return ResultVO.success("批量新增成功！");
    }

    @PostMapping("/batchDel")
    public ResultVO batchDel(@RequestBody @NotEmpty List<String> ids) {
        bizFieldService.deleteBatchIds(ids);
        return ResultVO.success("批量删除成功！");
    }
}
