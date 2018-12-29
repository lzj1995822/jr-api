package com.cloudkeeper.leasing.utils;

import org.junit.Test;

import static com.cloudkeeper.leasing.utils.Content.*;
import static com.cloudkeeper.leasing.utils.DefinitionUtil.PackageSuffix;
import static com.cloudkeeper.leasing.utils.DefinitionUtil.generateJavaFile;

/**
 * 自动生成模板代码，工具类
 * @author jerry
 */
public class GenerateTemplateCodeUtil {

    /** 表名*/
    static String TABLE_NAME = "jr_org_person";

    /** 创建liquibase xml 的id*/
    static final String CHANGE_SET_ID = "20180323-01";

    /** 类名*/
    static final String CLASS_NAME = "OrgPerson";

    /** 类注释*/
    static final String CLASS_EXPLAIN = "组织架构人员";

    /** 子工程名称*/
    static final String PROJECT_NAME = "identity";

    /** 注释 - 作者*/
    static String DOC_AUTHOR = "wj";

    /** 项目根目录 */
    static final String PROJECT_DIR = "D:\\jr-api\\jr-api\\";

    /** 生成domain*/
    @Test
    public void generateDomain() throws Exception {
        generateJavaFile(PackageSuffix.DOMAIN, getDomainContent());
    }

    /** 生成所有代码*/
    @Test
    public void generateMain() throws Exception {
        generateJavaFile(PackageSuffix.VO, getVOContent());
        generateJavaFile(PackageSuffix.DTO, getDTOContent());
        generateJavaFile(PackageSuffix.SEARCHABLE, getSearchableContent());
        generateJavaFile(PackageSuffix.REPOSITORY, getRepositoryContent());
        generateJavaFile(PackageSuffix.REPOSITORY_TEST, getRepositoryTestContent());
        generateJavaFile(PackageSuffix.SERVICE, getServiceContent());
        generateJavaFile(PackageSuffix.SERVICE_IMPL, getServiceImplContent());
        generateJavaFile(PackageSuffix.SERVICE_TEST, getServiceTestContent());
        generateJavaFile(PackageSuffix.CONTROLLER, getControllerContent());
        generateJavaFile(PackageSuffix.CONTROLLER_IMPL, getControllerImplContent());
        generateJavaFile(PackageSuffix.CONTROLLER_TEST, getControllerTestContent());
        System.out.println(getLiquibaseXML());
    }

    /** 生成liquibase*/
    @Test
    public void readDomain() {
        System.out.println(getLiquibaseXML());
    }

}
