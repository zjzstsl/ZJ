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
import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.biz.service.IBizModelService;

import java.util.List;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@RestController
@Validated
@RequestMapping("bizModel")
public class BizModelController extends BaseController<BizModel> {

    @Autowired
    private IBizModelService bizModelService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated({AddValidateGroup.class}) BizModel bizModel) {
        bizModelService.insert(bizModel);
        return ResultVO.success("新增成功！");
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated({UpdateValidateGroup.class}) BizModel bizModel) {
        bizModelService.updateById(bizModel);
        return ResultVO.success("修改成功！");
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        bizModelService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        BizModel bizModel = bizModelService.selectById(id);
        if (bizModel == null) {
            return ResultVO.error("00001", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", bizModel);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<BizModel> page){
        return  ResultVO.success("查询成功", bizModelService.selectPage(getPage(page), getCondition(page)));
    }

    @PostMapping("/batchAdd")
    public ResultVO batchAdd(@RequestBody @Validated({AddValidateGroup.class}) List<BizModel> bizModules) {
        bizModelService.insertBatch(bizModules);
        return ResultVO.success("批量新增成功！");
    }

    @PostMapping("/batchDel")
    public ResultVO batchDel(@RequestBody @NotEmpty List<String> ids) {
        bizModelService.deleteBatchIds(ids);
        return ResultVO.success("批量删除成功！");
    }
}
