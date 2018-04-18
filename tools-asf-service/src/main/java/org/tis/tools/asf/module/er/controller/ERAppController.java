package org.tis.tools.asf.module.er.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.validation.AddValidateGroup;
import org.tis.tools.core.validation.UpdateValidateGroup;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.asf.module.er.entity.ERApp;
import org.tis.tools.asf.module.er.service.IERAppService;

import java.util.List;

@RestController
@RequestMapping("erApp")
public class ERAppController extends BaseController<ERApp> {

    @Autowired
    private IERAppService appService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated({AddValidateGroup.class}) ERApp app) {
        appService.insert(app);
        return ResultVO.success("新增成功！");
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated({UpdateValidateGroup.class}) ERApp app) {
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
        ERApp app = appService.selectById(id);
        if (app == null) {
            return ResultVO.error("00001", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", app);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<ERApp> page){
        return  ResultVO.success("查询成功", appService.selectPage(getPage(page), getCondition(page)));
    }

    @PostMapping("/batchAdd")
    public ResultVO batchAdd(@RequestBody @Validated({AddValidateGroup.class}) List<ERApp> apps) {
        appService.insertBatch(apps);
        return ResultVO.success("批量新增成功！");
    }

    @PostMapping("/batchDel")
    public ResultVO batchDel(@RequestBody @NotEmpty List<String> ids) {
        appService.deleteBatchIds(ids);
        return ResultVO.success("批量删除成功！");
    }

    /**
     * 文件上传（单文件上传）
     *
     * @param file
     * @return
     *
     */
    @PostMapping(value = "/importERM")
    public ResultVO importERM(@RequestParam("file") MultipartFile file) {
        String xmlStr;
        if (!file.isEmpty()) {
            try {
                xmlStr = XML.toJSONObject(new String(file.getBytes())).toString();
            } catch (Exception e) {
                return ResultVO.error("文件解析失败.");
            }
        } else {
            return ResultVO.error("上传失败，文件不能为空.");
        }
        appService.parseERM(file.getName(), xmlStr);
        return ResultVO.success("上传成功！");
    }

}
