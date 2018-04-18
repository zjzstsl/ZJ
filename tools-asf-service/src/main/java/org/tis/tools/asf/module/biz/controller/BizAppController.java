package org.tis.tools.asf.module.biz.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.asf.module.biz.entity.BizApp;
import org.tis.tools.asf.module.biz.service.IBizAppService;
import org.tis.tools.core.validation.AddValidateGroup;
import org.tis.tools.core.validation.UpdateValidateGroup;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

import java.util.List;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@RestController
@RequestMapping("bizApp")
@Validated
public class BizAppController extends BaseController<BizApp> {

    @Autowired
    private IBizAppService appService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated({AddValidateGroup.class}) BizApp app) {
        appService.insert(app);
        return ResultVO.success("新增成功！");
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated({UpdateValidateGroup.class}) BizApp app) {
        appService.updateById(app);
        return ResultVO.success("修改成功！");
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        appService.deleteById(id);
        return ResultVO.success("删除成功");
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        BizApp app = appService.selectById(id);
        if (app == null) {
            return ResultVO.error("00001", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", app);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<BizApp> page){
        return  ResultVO.success("查询成功", appService.selectPage(getPage(page), getCondition(page)));
    }

    @PostMapping("/batchAdd")
    public ResultVO batchAdd(@RequestBody @Validated({AddValidateGroup.class}) List<BizApp> apps) {
        appService.insertBatch(apps);
        return ResultVO.success("批量新增成功！");
    }

    @PostMapping("/batchDel")
    public ResultVO batchDel(@RequestBody @NotEmpty List<String> ids) {
        appService.deleteBatchIds(ids);
        return ResultVO.success("批量删除成功！");
    }


}
