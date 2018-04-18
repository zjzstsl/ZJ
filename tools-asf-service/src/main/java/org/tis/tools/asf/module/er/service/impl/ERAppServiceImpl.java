package org.tis.tools.asf.module.er.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.asf.core.exception.CodeBuilderException;
import org.tis.tools.asf.module.er.dao.ERAppMapper;
import org.tis.tools.asf.module.er.entity.*;
import org.tis.tools.asf.module.er.service.IERAppService;
import org.tis.tools.asf.module.er.service.IERCategoryService;
import org.tis.tools.asf.module.er.service.IERColumnService;
import org.tis.tools.asf.module.er.service.IERTableService;
import org.tis.tools.asf.module.er.util.ErmUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * describe:
 *
 * @author zhaoch
 * @date 2018/4/4
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ERAppServiceImpl extends ServiceImpl<ERAppMapper, ERApp> implements IERAppService {

    @Autowired
    private IERCategoryService categoryService;

    @Autowired
    private IERTableService tableService;

    @Autowired
    private IERColumnService columnService;

    @Override
    public ERApp queryERAppDetailById(String erAppId) {
        // 验证对应ID的ER应用是否存在
        ERApp erApp = this.baseMapper.selectById(erAppId);
        if (erApp == null) {
            throw new CodeBuilderException("没有找到对应的ER应用");
        }
        EntityWrapper<ERCategory> cateWrapper = new EntityWrapper<>();
        cateWrapper.eq(ERCategory.COLUMN_APP_ID, erAppId);
        List<ERCategory> categoryList = categoryService.selectList(cateWrapper);
        Map<String, ERCategory> categoryMap = categoryList.stream().collect(Collectors.toMap(ERCategory::getId, c -> c));
        erApp.setCategoryList(categoryList);
        List<String> categoryIds = categoryList.stream().map(ERCategory::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(categoryIds)) {
            EntityWrapper<ERTable> tableWrapper = new EntityWrapper<>();
            tableWrapper.in(ERTable.COLUMN_CATEGORY_ID, categoryIds);
            List<ERTable> tableList = tableService.selectList(tableWrapper);
            tableList.stream().collect(Collectors.groupingBy(ERTable::getCategoryId))
                    .forEach((categoryId, tables) -> categoryMap.get(categoryId).setTableList(tables));
            Map<String, ERTable> tableMap = tableList.stream().collect(Collectors.toMap(ERTable::getId, t -> t));
            erApp.setTableList(tableList);
            List<String> tableIds = tableList.stream().map(ERTable::getId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(tableIds)) {
                EntityWrapper<ERColumn> columnWrapper = new EntityWrapper<>();
                columnWrapper.in(ERColumn.COLUMN_TABLE_ID, tableIds);
                List<ERColumn> columnList = columnService.selectList(columnWrapper);
                columnList.stream().collect(Collectors.groupingBy(ERColumn::getTableId))
                        .forEach((tableId, columns) -> {
                            ERColumns erColumns = new ERColumns();
                            erColumns.setNormalColumnList(columns);
                            tableMap.get(tableId).setColumns(erColumns);
                        });
                erApp.setColumnList(columnList);
            }
        }
        return erApp;
    }

    @Override
    public void parseERM(String appName, String xmlStr) {
        ERApp erApp = ErmUtils.parse(xmlStr);
        erApp.setName(appName);
        this.baseMapper.insert(erApp);
        erApp.getCategoryList().forEach(c -> {
            c.setId(null);
            c.setAppId(erApp.getId());
        });
        categoryService.insertBatch(erApp.getCategoryList());
        erApp.getCategoryList().forEach(c -> c.getTableList().forEach(t -> {
            t.setCategoryId(c.getId());
            t.setId(null);
        }));
        tableService.insertBatch(erApp.getTableList());
        erApp.getTableList().forEach(t -> t.getColumnList().forEach(c -> {
            c.setTableId(t.getId());
            c.setId(null);
        }));
        columnService.insertBatch(erApp.getColumnList());
    }

}
