package org.tis.tools.asf.module.biz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.asf.core.exception.CodeBuilderException;
import org.tis.tools.asf.module.biz.dao.BizAppMapper;
import org.tis.tools.asf.module.biz.dao.BizModuleMapper;
import org.tis.tools.asf.module.biz.entity.BizApp;
import org.tis.tools.asf.module.biz.entity.BizField;
import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.biz.entity.BizModule;
import org.tis.tools.asf.module.biz.service.IBizAppService;
import org.tis.tools.asf.module.biz.service.IBizFieldService;
import org.tis.tools.asf.module.biz.service.IBizModelService;
import org.tis.tools.asf.module.biz.service.IBizModuleService;
import org.tis.tools.asf.module.biz.util.ER2BizUtil;
import org.tis.tools.asf.module.er.dao.ERAppMapper;
import org.tis.tools.asf.module.er.entity.*;
import org.tis.tools.asf.module.er.service.IERAppService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BizAppServiceImpl extends ServiceImpl<BizAppMapper, BizApp> implements IBizAppService {

    @Autowired
    private ERAppMapper erAppMapper;

    @Autowired
    private IBizModuleService bizModuleService;

    @Autowired
    private IBizModelService bizModelService;

    @Autowired
    private IBizFieldService bizFieldService;

    @Autowired
    private IERAppService appService;

    @Override
    public void importErApp(String erAppId) {
        // 将ER应用转为BIZ应用
        ERApp erApp = appService.queryERAppDetailById(erAppId);
        BizApp bizApp = ER2BizUtil.erApp2BizApp(erApp);
        this.baseMapper.insert(bizApp);
        List<BizModule> moduleList = bizApp.getModuleList();
        if (CollectionUtils.isNotEmpty(moduleList)) {
            moduleList.forEach(m -> m.setAppId(bizApp.getId()));
            bizModuleService.insertBatch(moduleList);
            moduleList.forEach(m -> {
                List<BizModel> modelList = m.getModelList();
                if (CollectionUtils.isNotEmpty(modelList)) {
                    modelList.forEach(mo -> mo.setModuleId(m.getId()));
                }
            });
            List<BizModel> modelList = bizApp.getModelList();
            if (CollectionUtils.isNotEmpty(modelList)) {
                bizModelService.insertBatch(modelList);
                modelList.forEach(m -> {
                    List<BizField> fieldList = m.getFieldList();
                    if (CollectionUtils.isNotEmpty(fieldList)) {
                        fieldList.forEach(f -> f.setModelId(m.getId()));
                    }
                });
                bizFieldService.insertBatch(bizApp.getFieldList());
            }
        }
    }

    @Override
    public BizApp queryBizAppDetailById(String bizAppId) {
        // 验证对应ID的BIZ应用是否存在
        BizApp bizApp = this.baseMapper.selectById(bizAppId);
        if (bizApp == null) {
            throw new CodeBuilderException("没有找到对应的Biz应用");
        }
        EntityWrapper<BizModule> cateWrapper = new EntityWrapper<>();
        cateWrapper.eq(BizModule.COLUMN_APP_ID, bizAppId);
        List<BizModule> moduleList = bizModuleService.selectList(cateWrapper);
        Map<String, BizModule> moduleMap = moduleList.stream().collect(Collectors.toMap(BizModule::getId, c -> c));
        bizApp.setModuleList(moduleList);
        List<String> moduleIds = moduleList.stream().map(BizModule::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(moduleIds)) {
            EntityWrapper<BizModel> tableWrapper = new EntityWrapper<>();
            tableWrapper.in(BizModel.COLUMN_MODULE_ID, moduleIds);
            List<BizModel> modelList = bizModelService.selectList(tableWrapper);
            modelList.stream().collect(Collectors.groupingBy(BizModel::getModuleId))
                    .forEach((moduleId, models) -> moduleMap.get(moduleId).setModelList(models));
            Map<String, BizModel> modelMap = modelList.stream().collect(Collectors.toMap(BizModel::getId, t -> t));
            bizApp.setModelList(modelList);
            List<String> modelIds = modelList.stream().map(BizModel::getId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(modelIds)) {
                EntityWrapper<BizField> columnWrapper = new EntityWrapper<>();
                columnWrapper.in(BizField.COLUMN_MODEL_ID, modelIds);
                List<BizField> fieldList = bizFieldService.selectList(columnWrapper);
                fieldList.stream().collect(Collectors.groupingBy(BizField::getModelId))
                        .forEach((modelId, fields) -> modelMap.get(modelId).setFieldList(fields));
                bizApp.setFieldList(fieldList);
            }
        }
        return bizApp;
    }
}
