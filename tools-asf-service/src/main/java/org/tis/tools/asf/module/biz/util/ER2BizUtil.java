package org.tis.tools.asf.module.biz.util;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.tis.tools.asf.core.exception.CodeBuilderException;
import org.tis.tools.asf.module.biz.entity.BizApp;
import org.tis.tools.asf.module.biz.entity.BizField;
import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.biz.entity.BizModule;
import org.tis.tools.asf.module.er.entity.*;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/8
 **/
public class ER2BizUtil {

    public static BizApp erApp2BizApp(ERApp erApp) {
        BizApp bizApp = new BizApp();
        bizApp.setName(erApp.getName());
        bizApp.setDesc(erApp.getDesc());
        bizApp.setModelList(new ArrayList<>());
        bizApp.setFieldList(new ArrayList<>());
        List<BizModule> moduleList = new ArrayList<>();
        bizApp.setModuleList(moduleList);
        erApp.getCategoryList().forEach(c -> moduleList.add(erCategory2BizModule(c)));
        moduleList.forEach(m -> {
            if (CollectionUtils.isNotEmpty(m.getModelList())) {
                bizApp.getModelList().addAll(m.getModelList());
                m.getModelList().forEach(bizModel -> {
                    if (CollectionUtils.isNotEmpty(bizModel.getFieldList())) {
                        bizApp.getFieldList().addAll(bizModel.getFieldList());
                    }
                });
            }
        });
        return bizApp;
    }

    public static BizModule erCategory2BizModule(ERCategory erCategory) {
        BizModule bizModule = new BizModule();
        bizModule.setLogicName(erCategory.getName());
        try {
            bizModule.setPhysicalName(new PinyinTools().toPinYinAbbr(erCategory.getName()));
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new CodeBuilderException("模块名转换错误", e);
        }
        List<BizModel> modelList = new ArrayList<>();
        bizModule.setModelList(modelList);
        erCategory.getTableList().forEach(t -> modelList.add(erTable2BizModel(t)));
        return bizModule;
    }

    public static BizModel erTable2BizModel(ERTable erTable) {
        BizModel bizModel = new BizModel();
        bizModel.setLogicalName(erTable.getLogicalName());
        bizModel.setPhysicalName(GenerateUtils.underline2Camel(erTable.getPhysicalName(), true));
        bizModel.setDesc(erTable.getDesc());
        List<BizField> fieldList = new ArrayList<>();
        bizModel.setFieldList(fieldList);
        ERColumns columns = erTable.getColumns();
        if (columns != null && columns.getNormalColumnList() != null) {
            columns.getNormalColumnList().forEach(c -> fieldList.add(erColumn2BizField(c)));
        }
        return bizModel;
    }

    public static BizField erColumn2BizField(ERColumn erColumn) {
        BizField bizField = new BizField();
        bizField.setOrder(erColumn.getOrder());
        bizField.setDesc(erColumn.getDesc());
        bizField.setLogicalName(erColumn.getLogicalName());
        bizField.setPhysicalName(GenerateUtils.underline2Camel(erColumn.getPhysicalName(), true));
        bizField.setType(erColumn.getType().getFieldType());
        bizField.setLength(erColumn.getLength());
        bizField.setPrimaryKey(erColumn.getPrimaryKey());
        bizField.setDefaultValue(erColumn.getDefaultValue());
        return bizField;
    }
}
