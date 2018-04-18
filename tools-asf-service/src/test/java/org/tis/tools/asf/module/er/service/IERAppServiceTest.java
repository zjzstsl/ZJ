package org.tis.tools.asf.module.er.service;

import org.json.JSONException;
import org.json.XML;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.asf.base.BaseTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IERAppServiceTest extends BaseTest {

    @Autowired
    private IERAppService appService;

    @Test
    public void parseERM() throws IOException, JSONException {
        // 1.导入ERM生成ER模型
        String filePath = "D:\\GitSpace\\TTT\\doc\\ABF2018-oracle.erm";
        File file = new File(filePath);
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s;
        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s).append("\n");//将读取的字符串添加换行符后累加存放在缓存中
        }
        bReader.close();
        String str = sb.toString();
        String xmlStr = XML.toJSONObject(str).toString();
        String name = file.getName().substring(0, file.getName().lastIndexOf("."));
        appService.parseERM(name, xmlStr);
    }
}