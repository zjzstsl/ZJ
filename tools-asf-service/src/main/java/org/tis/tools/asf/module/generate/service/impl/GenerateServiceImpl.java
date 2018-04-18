package org.tis.tools.asf.module.generate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.asf.core.exception.CodeBuilderException;
import org.tis.tools.asf.module.biz.entity.BizApp;
import org.tis.tools.asf.module.biz.entity.BizModel;
import org.tis.tools.asf.module.biz.service.IBizAppService;
import org.tis.tools.asf.module.generate.layout.controller.CommonController;
import org.tis.tools.asf.module.generate.layout.dao.CommonMapper;
import org.tis.tools.asf.module.generate.layout.entity.CommonEntity;
import org.tis.tools.asf.module.generate.layout.service.CommonService;
import org.tis.tools.asf.module.generate.layout.service.CommonServiceImpl;
import org.tis.tools.asf.module.generate.layout.dao.CommonMapperXml;
import org.tis.tools.asf.module.generate.service.IGenerateService;
import org.tis.tools.asf.module.generate.tools.FileUtils;
import org.tis.tools.asf.module.generate.tools.GenerateUtils;

import java.io.IOException;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/9
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GenerateServiceImpl implements IGenerateService {

    private final static String JAVA_FILE_SUFFIX = ".java";
    private final static String XML_FILE_SUFFIX = ".xml";

    @Autowired
    private IBizAppService bizAppService;

    @Override
    public void generate(String bizAppId, String packageName) {
        BizApp bizApp = bizAppService.queryBizAppDetailById(bizAppId);
        bizApp.getModuleList().forEach(m ->{
            String p = packageName + ".module." + m.getPhysicalName().toLowerCase();
            m.getModelList().forEach(md -> {
                try {
                    process(p, md);
                } catch (IOException e) {
                    throw new CodeBuilderException("生成代码失败！", e);
                }
            });
        });
    }

    private void process(String p, BizModel m) throws IOException {
        CommonEntity entity = CommonEntity.instance(p, m);
        FileUtils.createFile(entity.getName() + JAVA_FILE_SUFFIX,
                entity.genFileContent(),
                FileUtils.package2Path(entity.getPackageInfo()));
        CommonMapper mapper = CommonMapper.instance(p, m);
        FileUtils.createFile(mapper.getName() + JAVA_FILE_SUFFIX,
                mapper.genFileContent(),
                FileUtils.package2Path(mapper.getPackageInfo()));
        CommonService service = CommonService.instance(p, m);
        FileUtils.createFile(service.getName() + JAVA_FILE_SUFFIX,
                service.genFileContent(),
                FileUtils.package2Path(service.getPackageInfo()));
        CommonServiceImpl serviceImpl = CommonServiceImpl.instance(p, m);
        FileUtils.createFile(serviceImpl.getName() + JAVA_FILE_SUFFIX,
                serviceImpl.genFileContent(),
                FileUtils.package2Path(serviceImpl.getPackageInfo()));
        CommonController controller = CommonController.instance(p, m);
        FileUtils.createFile(controller.getName() + JAVA_FILE_SUFFIX,
                controller.genFileContent(),
                FileUtils.package2Path(controller.getPackageInfo()));
        CommonMapperXml mapperXml = CommonMapperXml.instance(p, m);
        FileUtils.createFile(mapperXml.getName() + XML_FILE_SUFFIX,
                mapperXml.genFileContent(),
                FileUtils.package2Path(mapperXml.getPackageInfo()));
    }

}
