package org.tis.tools.asf.module.er.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.XML;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.asf.module.er.entity.*;
import org.tis.tools.asf.module.er.entity.enums.ERColumnType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ErmUtils {

    public static String xml2String(String filepath) throws JSONException {
        File f= new File(filepath);
        String xmlstr;
        try {
             xmlstr = FileUtils.readFileToString(f, Charset.defaultCharset());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        }
        return XML.toJSONObject(xmlstr).toString();
    }

    public static String xml2Json(String xmlStr) throws JSONException {
        return XML.toJSONObject(xmlStr).toString();
    }

    public static ERApp parse(String xmlString) {

        ParserConfig.getGlobalInstance().putDeserializer(ERColumnType.class, new CommonEnumDeserializer());
        com.alibaba.fastjson.JSONObject diagram = JSON.parseObject(xmlString).getJSONObject("diagram");
        List<ERTable> erTables = JSONArray.parseArray(diagram.getJSONObject("contents").getString("table"), ERTable.class);
        Map<String, ERWord> wordMap = JSONArray.parseArray(diagram.getJSONObject("dictionary")
                .getString("word"), ERWord.class)
                .stream().collect(Collectors.toMap(ERWord::getId, w -> w));
        List<ERCategory> erCategories = JSONArray.parseArray(diagram
                .getJSONObject("settings")
                .getJSONObject("category_settings")
                .getJSONObject("categories")
                .getString("category"), ERCategory.class);
        Map<String, List<ERColumn>> refColumnsMap = new HashMap<>(16);
        Map<String, ERColumn> columnMap = new HashMap<>(16);
        erTables.forEach(t -> {
            for (ERCategory c : erCategories) {
                if (c.getTableIds().contains(t.getId())) {
                    t.setCategoryId(c.getId());
                    if (c.getTableList() == null) {
                        List<ERTable> tables = new ArrayList<>();
                        c.setTableList(tables);
                    }
                    c.getTableList().add(t);
                    break;
                }
            }
            for (ERColumn c : t.getColumns().getNormalColumnList()) {
                columnMap.put(c.getId(), c);
                c.setTableId(t.getId());
                String wordId = c.getWordId();
                if (wordId != null && wordMap.get(wordId) != null) {
                    ERWord w = wordMap.get(wordId);
                    c.setLogicalName(w.getLogicalName());
                    c.setPhysicalName(w.getPhysicalName());
                    c.setType(ERColumnType.getColumnType(w.getType()));
                    c.setDesc(w.getDescription());
                    c.setLength(w.getLength());
                }
                String refColumnId = c.getReferencedColumn();
                if (StringUtils.isNotBlank(refColumnId)) {
                    ERColumn refColumn = columnMap.get(refColumnId);
                    if (refColumn != null) {
                        ERWord word = wordMap.get(refColumn.getWordId());
                        c.setLength(word.getLength());
                        c.setDesc(word.getDescription());
                    } else {
                        refColumnsMap.computeIfAbsent(c.getReferencedColumn(), k -> new ArrayList<>());
                        refColumnsMap.get(c.getReferencedColumn()).add(c);
                    }
                }
                t.setColumnList(t.getColumns().getNormalColumnList());
            }
        });
        // 处理引用
        refColumnsMap.forEach((id, list) -> list.forEach(c -> {
            ERWord word = wordMap.get(columnMap.get(id).getWordId());
            c.setLength(word.getLength());
            c.setDesc(word.getDescription());
        }));
        List<ERColumn> columns = erTables.stream()
                .map(ERTable::getColumns)
                .map(ERColumns::getNormalColumnList)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        ERApp erApp = new ERApp();
        erApp.setColumnList(columns);
        erApp.setCategoryList(erCategories);
        erApp.setTableList(erTables);

        return erApp;
    }

}
